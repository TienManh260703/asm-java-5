package com.example.shopapp.mapper;

import com.example.shopapp.dto.response.OrderResponse;
import com.example.shopapp.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse toOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setCustomerId(order.getCustomer().getId());
        response.setCustomerName(order.getCustomer().getName());
        response.setPhoneNumber(order.getCustomer().getPhoneNumber());
        response.setStaffId(order.getStaff().getId());
        response.setStaffUserName(order.getStaff().getUserName());
        return response;
    }
}
