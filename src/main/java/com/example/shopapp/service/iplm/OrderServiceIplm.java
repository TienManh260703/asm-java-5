package com.example.shopapp.service.iplm;

import com.example.shopapp.dto.request.OrderRequest;
import com.example.shopapp.dto.response.OrderResponse;
import com.example.shopapp.mapper.OrderMapper;
import com.example.shopapp.model.Order;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceIplm implements IOrderService {

    OrderRepository orderRepository;
    OrderMapper orderMapper;
    StaffServiceIplm staffService;
    CustomerServiceIplm customerService;

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
    public void create(OrderRequest request) {
        Order order = new Order();
        order.setStaff(staffService.findByUserName(request.getStaffUserName()));
        order.setCustomer(customerService.findByPhoneNumber(request.getPhoneNumber()));
        orderRepository.save(order);
    }

    @Override
    public void updateStatus(String id, Integer status) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order existingOrder = orderOptional.get();
            existingOrder.setStatus(status);
            if (status == 1) {
                existingOrder.setDateOfPayment(LocalDateTime.now());
            }
            orderRepository.save(existingOrder);
        }
    }

    @Override
    public void pay(String id) {

    }
}
