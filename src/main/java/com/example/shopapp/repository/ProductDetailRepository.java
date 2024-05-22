package com.example.shopapp.repository;

import com.example.shopapp.model.Product;
import com.example.shopapp.model.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, String> {

    Page<ProductDetail> findAll (Pageable pageable);
    Page<ProductDetail> findByDeletedFalse(Pageable  pageable);
    Page<ProductDetail> findAllByProductId(String productId , Pageable pageable);
    @Query("SELECT pd FROM ProductDetail pd WHERE pd.product.id = :productId AND pd.price BETWEEN :minPrice AND :maxPrice")
    Page<ProductDetail> findByProductIdAndPriceRange(String productId, float minPrice, float maxPrice, Pageable pageable);

    @Query("SELECT pd FROM ProductDetail pd WHERE pd.product.id = :productId AND pd.price BETWEEN :minPrice AND :maxPrice")
    // Test API
    List<ProductDetail> findByProductIdAndPriceRange(String productId, float minPrice, float maxPrice);
}
