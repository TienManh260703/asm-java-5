package com.example.shopapp.mapper;

import com.example.shopapp.dto.response.ProductDetailResponse;
import com.example.shopapp.model.Product;
import com.example.shopapp.model.ProductDetail;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailMapper {

    public ProductDetailResponse toProductDetailResponse(ProductDetail request) {
        return ProductDetailResponse
                .builder()
                .id(request.getId())
                .code(request.getCode())
                .name(request.getProduct().getName() + " [ " + request.getColor().getName() + " - " + request.getSize().getName() + " ] ")
                .colorName(request.getColor().getName())
                .sizeName(request.getSize().getName())
                .productName(request.getProduct().getName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
//                .status(request.getStatus().getDescription())
                .deleted(request.getDeleted())
                .build();
    }
}
