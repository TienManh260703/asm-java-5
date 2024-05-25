package com.example.shopapp.repository;

import com.example.shopapp.model.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    Page<OrderDetail> findAllByOrderId(String orderId, Pageable pageable);
    List<OrderDetail> findAllByOrderId(String id);
}
