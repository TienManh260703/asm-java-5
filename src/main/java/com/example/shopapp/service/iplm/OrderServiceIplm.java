package com.example.shopapp.service.iplm;

import com.example.shopapp.dto.request.OrderRequest;
import com.example.shopapp.dto.response.OrderDetailResponse;
import com.example.shopapp.dto.response.OrderResponse;
import com.example.shopapp.mapper.OrderMapper;
import com.example.shopapp.model.Customer;
import com.example.shopapp.model.Order;
import com.example.shopapp.model.OrderDetail;
import com.example.shopapp.model.ProductDetail;
import com.example.shopapp.repository.OrderRepository;
import com.example.shopapp.service.IColorService;
import com.example.shopapp.service.IOrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceIplm implements IOrderService {

    OrderRepository orderRepository;
    OrderMapper orderMapper;
    StaffServiceIplm staffService;
    CustomerServiceIplm customerService;
    OrderDetailServiceIplm orderDetailService;

    @Override
    public Page<Order> getOrders(Pageable pageable) {

        return orderRepository.findAll(pageable);
    }

    @Override
    public OrderResponse getOrder(String id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            return orderMapper.toOrderResponse(orderOptional.get());
        }
        return new OrderResponse();
    }

    @Override
    public void update(String id, OrderRequest order) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order existingOrder = orderOptional.get();
            existingOrder.setCustomer(
                    customerService.findByPhoneNumber(
                            order.getPhoneNumber()));
        }
    }

    @Override
    public boolean create(OrderRequest request) {
        Order order = new Order();
        order.setStaff(staffService.findByUserName(request.getStaffUserName()));
        Customer customer = customerService.findByPhoneNumber(request.getPhoneNumber());
        if (customer != null) {
            order.setCustomer(customer);
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public void updateStatus(String id, Integer status) {
        // update hủy sẽ xóa hết orderDetail
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order existingOrder = orderOptional.get();
            existingOrder.setStatus(status);
            if (status == 1) {
                existingOrder.setDateOfPayment(LocalDateTime.now());
            }

            // chưa xong asm2 tt
//            if (status == 2) {
//                List<OrderDetailResponse> orderDetailResponses = orderDetailService.getOrdersDetailList(id);
//                if (orderDetailResponses != null && !orderDetailResponses.isEmpty()) {
//                    List<String> orderDetailIds = orderDetailResponses.stream()
//                            .map(OrderDetailResponse::getOrderId)
//                            .collect(Collectors.toList());
//
//                    for (String orderId : orderDetailIds){
//                        orderDetailService.deleted(orderId);
//                    }
//                }
//            }
            orderRepository.save(existingOrder);

        }
    }

    @Override
    public void pay(String id) {

    }
}
