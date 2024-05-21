package com.example.shopapp.repository;

import com.example.shopapp.model.Color;
import com.example.shopapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

}
