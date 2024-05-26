package com.example.shopapp.service.iplm;

import com.example.shopapp.dto.request.OrderRequest;
import com.example.shopapp.dto.response.OrderDetailResponse;
import com.example.shopapp.dto.response.OrderResponse;
import com.example.shopapp.dto.response.ProductDetailResponse;
import com.example.shopapp.mapper.OrderMapper;
import com.example.shopapp.mapper.ProductDetailMapper;
import com.example.shopapp.model.Order;
import com.example.shopapp.model.OrderDetail;
import com.example.shopapp.model.ProductDetail;
import com.example.shopapp.repository.OrderDetailRepository;
import com.example.shopapp.repository.OrderRepository;
import com.example.shopapp.repository.ProductDetailRepository;
import com.example.shopapp.service.ISellService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SellService implements ISellService {

    OrderRepository orderRepository;
    OrderMapper orderMapper;

    ProductDetailRepository productDetailRepository;
    ProductDetailMapper productDetailMapper;

    OrderDetailRepository orderDetailRepository;

    @Override
    public Page<OrderResponse> findOrderByStatus(Integer status, Pageable pageable) {
        return orderRepository.findByStatus(status, pageable).map(order -> {
                    OrderResponse orderResponse = orderMapper.toOrderResponse(order);
                    List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderId(orderResponse.getId());
                    Float totalMoney = orderDetails.stream()
                            .map(orderDetail -> orderDetail.getPrice() * orderDetail.getQuantity())
                            .reduce(0f, Float::sum);
                    orderResponse.setTotalMoney(totalMoney);
                    return orderResponse;
                }
        );
    }

    @Override
    public Page<ProductDetailResponse> findProductByStatus(Pageable pageable) {
        return productDetailRepository.findByDeletedFalse(pageable).map(
                productDetail -> productDetailMapper.toProductDetailResponse(productDetail)
        );
    }

    /// bot
    @Override
    public Page<OrderDetailResponse> findOrderDetailByStatus(Boolean status, Pageable pageable) {
        return null;
    }

    @Override
    public List<OrderDetailResponse> findOrderByOrderId(String id) {
        return orderDetailRepository.findAllByOrderId(id).stream().map(
                orderDetail -> orderMapper.toOrderDetailResponse(orderDetail)
        ).toList();
    }

    @Override
    public OrderResponse getOrderById(String orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            OrderResponse orderResponse = orderMapper.toOrderResponse(order);
            List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderId(orderId);
            Float totalMoney = orderDetails.stream()
                    .map(orderDetail -> orderDetail.getPrice() * orderDetail.getQuantity())
                    .reduce(0f, Float::sum);
            orderResponse.setTotalMoney(totalMoney);
            return orderResponse;
        }
        return null;
    }

    @Override
    public void addOrderDetail(String productDetailId, String idOrder) {
        Optional<ProductDetail> productDetailOptional = productDetailRepository.findById(productDetailId);
        if (orderDetailRepository.existsByOrderIdAndProductDetailId(idOrder, productDetailId) ) {
            OrderDetail orderDetail = orderDetailRepository.findByProductDetailIdAndOrderId(productDetailId, idOrder);
            if((orderDetail.getQuantity() + 1)<=productDetailOptional.get().getQuantity()){
                orderDetail.setQuantity(orderDetail.getQuantity() + 1);
                orderDetailRepository.save(orderDetail);
            }
            return;
        }

        Optional<Order> orderOptional = orderRepository.findById(idOrder);

        if (orderOptional.isPresent() && productDetailOptional.isPresent()) {
            Order existingOrder = orderOptional.get();
            ProductDetail existingProductDetail = productDetailOptional.get();
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(existingOrder);
            orderDetail.setProductDetail(existingProductDetail);
            orderDetail.setQuantity(1);
            orderDetail.setPrice(existingProductDetail.getPrice());
            orderDetailRepository.save(orderDetail);
        }

    }

    @Override
    public void updateOrderDetailQuantity(String orderDetailId, Integer quantity) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(orderDetailId);
        if (optionalOrderDetail.isPresent()) {
            OrderDetail orderDetail = optionalOrderDetail.get();
            orderDetail.setQuantity(quantity);
            orderDetailRepository.save(orderDetail);
        }
    }

    @Override
    public void pay(OrderRequest request) {
        Optional<Order> orderOptional = orderRepository.findById(request.getId());
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatus(1);
            order.setDateOfPayment(LocalDateTime.now());

            List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderId(order.getId());
            for (OrderDetail orderDetail : orderDetails) {
                Optional<ProductDetail> productDetailOptional = productDetailRepository
                        .findById(
                                orderDetail.getProductDetail().getId()
                        );
                if (productDetailOptional.isPresent()) {
                    ProductDetail productDetail = productDetailOptional.get();
                    int updateQuantity = productDetail.getQuantity() - orderDetail.getQuantity();
                    productDetail.setQuantity(updateQuantity);
                    if (updateQuantity == 0) {
                        productDetail.setDeleted(true);
                    }
                    productDetailRepository.save(productDetail);
                }
            }
            orderRepository.save(order);
        }
    }



    @Override
    public void delete(String orderDetailId) {
        orderDetailRepository.deleteById(orderDetailId);
    }
}
