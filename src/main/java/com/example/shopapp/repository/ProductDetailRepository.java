package com.example.shopapp.repository;

import com.example.shopapp.model.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, String> {

    Page<ProductDetail> findAll (Pageable pageable);
    Page<ProductDetail> findByDeletedFalse(Pageable  pageable);

}
