package com.example.shopapp.service;

import com.example.shopapp.dto.request.OrderRequest;
import com.example.shopapp.dto.response.OrderDetailResponse;
import com.example.shopapp.dto.response.OrderResponse;
import com.example.shopapp.dto.response.ProductDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISellService {
    Page<OrderResponse> findOrderByStatus(Integer  status , Pageable pageable);
    Page<ProductDetailResponse> findProductByStatus( Pageable pageable);
    Page<OrderDetailResponse> findOrderDetailByStatus(Boolean status  , Pageable pageable);
    List<OrderDetailResponse> findOrderByOrderId (String id );
    OrderResponse getOrderById(String orderId);
    void addOrderDetail ( String productDetailId, String idOrder );// sua lai
    void updateOrderDetailQuantity (String orderDetailId , Integer quantity );
void pay(OrderRequest request);
    void delete(String orderDetailId);
}
