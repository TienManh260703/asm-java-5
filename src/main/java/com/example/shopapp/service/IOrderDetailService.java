package com.example.shopapp.service;

import com.example.shopapp.dto.request.OrderDetailRequest;
import com.example.shopapp.dto.request.OrderRequest;
import com.example.shopapp.dto.response.OrderDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderDetailService {
    Page<OrderDetailResponse> getOrdersDetail(String id, Pageable pageable);
    List<OrderDetailResponse> getOrdersDetailList(String id);
    OrderDetailResponse getOrder(String id);

    void update(String id, OrderDetailRequest request);

    void create(OrderDetailRequest request);

    void deleted(String id);
}
