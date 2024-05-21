package com.example.shopapp.repository;

import com.example.shopapp.model.Color;
import com.example.shopapp.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, String> {

}
