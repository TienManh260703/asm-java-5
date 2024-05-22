package com.example.shopapp.mapper;

import com.example.shopapp.dto.request.ProductDetailRequest;
import com.example.shopapp.dto.response.ProductDetailResponse;
import com.example.shopapp.model.Color;
import com.example.shopapp.model.Product;
import com.example.shopapp.model.ProductDetail;
import com.example.shopapp.model.Size;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailMapper {

    public ProductDetailResponse toProductDetailResponse(ProductDetail request) {
        return ProductDetailResponse
                .builder()
                .id(request.getId())
                .code(request.getCode())
                .name(request.getProduct().getName() + " [ " + request.getColor().getName() + " - " + request.getSize().getName() + " ] ")
                .colorId(request.getColor().getId())
                .colorName(request.getColor().getName())
                .sizeId(request.getSize().getId())
                .sizeName(request.getSize().getName())
                .productId(request.getProduct().getId())
                .productName(request.getProduct().getName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
//                .status(request.getStatus().getDescription())
                .deleted(request.getDeleted())
                .build();
    }

    public ProductDetail toProductDetail(ProductDetailRequest request) {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setId(request.getId());
        productDetail.setCode(request.getCode());
        productDetail.setPrice(request.getPrice());
        productDetail.setQuantity(request.getQuantity());
        productDetail.setProduct(Product.builder().id(request.getProductId()).build());
        productDetail.setColor(Color.builder().id(request.getColorId()).build());
        productDetail.setSize(Size.builder().id(request.getSizeId()).build());
        return productDetail;
    }

    public void toUpdate  (ProductDetail productDetail , ProductDetailRequest request){
        productDetail.setPrice(request.getPrice());
        productDetail.setQuantity(request.getQuantity());
        productDetail.setProduct(Product.builder().id(request.getProductId()).build());
        productDetail.setColor(Color.builder().id(request.getColorId()).build());
        productDetail.setSize(Size.builder().id(request.getSizeId()).build());
    }
}
