package com.example.shopapp.service;

import com.example.shopapp.dto.request.OrderRequest;
import com.example.shopapp.dto.response.OrderResponse;
import com.example.shopapp.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {
    Page<Order> getOrders(Pageable pageable);
    OrderResponse getOrder (String id);
    void  update(String id, OrderRequest order);
    boolean  create(OrderRequest order);
    void updateStatus(String id, Integer status);
    void pay(String id);
}
