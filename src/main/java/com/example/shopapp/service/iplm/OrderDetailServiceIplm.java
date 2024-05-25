package com.example.shopapp.service.iplm;

import com.example.shopapp.dto.request.OrderDetailRequest;
import com.example.shopapp.dto.request.OrderRequest;
import com.example.shopapp.dto.response.OrderDetailResponse;
import com.example.shopapp.dto.response.OrderResponse;
import com.example.shopapp.mapper.OrderMapper;
import com.example.shopapp.model.Order;
import com.example.shopapp.model.OrderDetail;
import com.example.shopapp.model.ProductDetail;
import com.example.shopapp.repository.OrderDetailRepository;
import com.example.shopapp.repository.ProductDetailRepository;
import com.example.shopapp.service.IColorService;
import com.example.shopapp.service.IOrderDetailService;
import com.example.shopapp.service.IOrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailServiceIplm implements IOrderDetailService {
    OrderDetailRepository orderDetailRepository;
    ProductDetailRepository productDetailRepository;
    OrderMapper orderMapper;

    @Override
    public Page<OrderDetailResponse> getOrdersDetail(String orderId, Pageable pageable) {
        return orderDetailRepository.findAllByOrderId(orderId, pageable).map(
                orderDetail -> orderMapper.toOrderDetailResponse(orderDetail)
        );
    }

    @Override
    public List<OrderDetailResponse> getOrdersDetailList(String id) {
        return orderDetailRepository.findAllByOrderId(id).stream().map(
                orderDetail -> orderMapper.toOrderDetailResponse(orderDetail)
        ).toList();
    }

    @Override
    public OrderDetailResponse getOrder(String id) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(id);
        if (optionalOrderDetail.isPresent()) {
            return orderMapper.toOrderDetailResponse(optionalOrderDetail.get());
        }
        return new OrderDetailResponse();
    }

    @Override
    public void update(String id, OrderDetailRequest request) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(id);
        if (optionalOrderDetail.isPresent()) {
            OrderDetail orderDetail = optionalOrderDetail.get();
            orderDetail.setQuantity(request.getQuantity());
        }

    }

    @Override
    public void create(OrderDetailRequest request) {
        OrderDetail orderDetail = new OrderDetail();
        Order order = new Order();
        order.setId(request.getOrderId());
        ProductDetail productDetail = productDetailRepository.findById(request.getOrderId()).orElse(null);
        if (productDetail == null) {
            return;
        }
        orderDetail.setProductDetail(productDetail);
        orderDetail.setOrder(order);
        orderDetail.setQuantity(request.getQuantity());
        orderDetail.setPrice(productDetail.getPrice());
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public void deleted(String id) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(id);
        if (optionalOrderDetail.isPresent()) {
            OrderDetail orderDetail = optionalOrderDetail.get();
            orderDetail.setStatus(!orderDetail.getStatus());
            orderDetailRepository.save(orderDetail);
        }
    }
}
