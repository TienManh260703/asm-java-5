package com.example.shopapp.service.iplm;

import com.example.shopapp.dto.request.ProductDetailRequest;
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

import java.util.List;
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
                .findAll(pageable)
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
    public void createProductDetail(ProductDetailRequest request) {
        productDetailRepository.save(productDetailMapper.toProductDetail(request));
    }

    @Override
    public void updateProductDetail(String id, ProductDetailRequest request) {
        Optional<ProductDetail> productDetailOptional = productDetailRepository.findById(id);
        if (productDetailOptional.isPresent()) {
            ProductDetail existingProductDetail = productDetailOptional.get();
            productDetailMapper.toUpdate(existingProductDetail, request);
            productDetailRepository.save(existingProductDetail);
        }
    }

    @Override
    public void deleted(String id) {
        Optional<ProductDetail> productDetailOptional = productDetailRepository.findById(id);
        if (productDetailOptional.isPresent()) {
            ProductDetail existingProductDetail = productDetailOptional.get();
            existingProductDetail.setDeleted(!existingProductDetail.getDeleted());
            productDetailRepository.save(existingProductDetail);
        }
    }

    @Override
    public Page<ProductDetail> findByDeletedFalse(Pageable pageable) {
        return productDetailRepository.findByDeletedFalse(pageable);
    }

    @Override
    public Page<ProductDetailResponse> findAllByProductId(String productId, Pageable pageable) {
        return productDetailRepository.findAllByProductId(productId, pageable).map(
                productDetail -> productDetailMapper.toProductDetailResponse(productDetail)
        );
    }

    @Override
    public Page<ProductDetailResponse> findByPriceRange(String productId, float minPrice, float maxPrice, Pageable pageable) {
          return productDetailRepository
                .findByProductIdAndPriceRange(productId, minPrice, maxPrice, pageable)
                .map(productDetail ->
                        productDetailMapper.toProductDetailResponse(productDetail)
                );
    }

    @Override
    public List<ProductDetail> findByProductIdAndPriceRange(String productId, float minPrice, float maxPrice) {
        return productDetailRepository.findByProductIdAndPriceRange(productId, minPrice, maxPrice);
    }


}
