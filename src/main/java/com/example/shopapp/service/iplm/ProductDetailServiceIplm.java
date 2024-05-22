package com.example.shopapp.service.iplm;

import com.example.shopapp.dto.response.ProductDetailResponse;
import com.example.shopapp.mapper.ProductDetailMapper;
import com.example.shopapp.model.ProductDetail;
import com.example.shopapp.repository.ProductDetailRepository;
import com.example.shopapp.service.IProductDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductDetailServiceIplm implements IProductDetailService {

    ProductDetailRepository productDetailRepository;
    ProductDetailMapper productDetailMapper;


    @Override
    public Page<ProductDetailResponse> getProductsDetail(Pageable pageable) {
        return productDetailRepository
                .findByDeletedFalse(pageable)
                .map(productDetail ->
                        productDetailMapper.toProductDetailResponse(productDetail)
                );
    }

    @Override
    public ProductDetailResponse getProductDetail(String id) {
        Optional<ProductDetail> productDetailOptional = productDetailRepository.findById(id);
        ProductDetailResponse response = new ProductDetailResponse();
        if (productDetailOptional.isPresent()) {
            response = productDetailMapper.toProductDetailResponse(productDetailOptional.get());
        }
        return response;
    }

    @Override
    public void createProductDetail(ProductDetail productDetail) {
        productDetailRepository.save(productDetail);
    }

    @Override
    public void updateProductDetail(String id, ProductDetail productDetail) {
        Optional<ProductDetail> productDetailOptional = productDetailRepository.findById(id);
        if (productDetailOptional.isPresent()) {
            ProductDetail existingProductDetail = productDetailOptional.get();
            existingProductDetail.setProduct(productDetail.getProduct());
            existingProductDetail.setColor(productDetail.getColor());
            existingProductDetail.setSize(productDetail.getSize());
            existingProductDetail.setQuantity(productDetail.getQuantity());
            existingProductDetail.setPrice(productDetail.getPrice());
//            existingProductDetail.setStatus(productDetail.getStatus());
            productDetailRepository.save(existingProductDetail);
        }
    }

    @Override
    public void deleted(String id) {

    }

    @Override
    public Page<ProductDetail> findByDeletedFalse(Pageable pageable) {
        return productDetailRepository.findByDeletedFalse(pageable);
    }
}
