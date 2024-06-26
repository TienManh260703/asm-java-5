package com.example.shopapp.service;

import com.example.shopapp.dto.request.ProductDetailRequest;
import com.example.shopapp.dto.response.ProductDetailResponse;
import com.example.shopapp.model.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductDetailService {
    Page<ProductDetailResponse> getProductsDetail(Pageable pageable);

    ProductDetailResponse getProductDetail(String id);

    void createProductDetail(ProductDetailRequest request);

    void updateProductDetail(String id, ProductDetailRequest request);

    void deleted(String id);
    Page<ProductDetail> findByDeletedFalse (Pageable pageable);
    Page<ProductDetailResponse> findByDeletedFalseResponse (Pageable pageable);
    Page<ProductDetailResponse> findAllByProductId(String productId , Pageable pageable);
    Page<ProductDetailResponse> findByPriceRange( String prodiutId,float minPrice, float maxPrice, Pageable pageable);
    List<ProductDetail> findByProductIdAndPriceRange(String productId, float minPrice, float maxPrice);
}
