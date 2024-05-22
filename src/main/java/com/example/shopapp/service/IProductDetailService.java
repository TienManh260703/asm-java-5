package com.example.shopapp.service;

import com.example.shopapp.dto.response.ProductDetailResponse;
import com.example.shopapp.model.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductDetailService {
    Page<ProductDetailResponse> getProductsDetail(Pageable pageable);

    ProductDetailResponse getProductDetail(String id);

    void createProductDetail(ProductDetail productDetail);

    void updateProductDetail(String id, ProductDetail productDetail);

    void deleted(String id);
    Page<ProductDetail> findByDeletedFalse (Pageable pageable);
}
