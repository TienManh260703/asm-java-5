package com.example.shopapp.mapper;

import com.example.shopapp.dto.request.OrderDetailRequest;
import com.example.shopapp.dto.response.OrderDetailResponse;
import com.example.shopapp.dto.response.OrderResponse;
import com.example.shopapp.model.Order;
import com.example.shopapp.model.OrderDetail;
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

    public OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail) {
        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
        orderDetailResponse.setId(orderDetail.getId());
        orderDetailResponse.setOrderId(orderDetail.getOrder().getId());
        orderDetailResponse.setProductDetailId(orderDetail.getProductDetail().getId());
        orderDetailResponse.setProductDetailName(
                orderDetail.getProductDetail().getProduct().getName() + " [ " +
                        orderDetail.getProductDetail().getColor().getName() + " - " +
                        orderDetail.getProductDetail().getSize().getName() + " ]"
        );
        orderDetailResponse.setMaxQuantity(orderDetail.getProductDetail().getQuantity());
        orderDetailResponse.setPrice(orderDetail.getPrice());
        orderDetailResponse.setQuantity(orderDetail.getQuantity());
        orderDetailResponse.setStatus(orderDetail.getStatus());
        //
        orderDetailResponse.setStatusOrder(orderDetail.getOrder().getStatus());
        return orderDetailResponse;
    }
}
