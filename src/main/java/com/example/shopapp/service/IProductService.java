package com.example.shopapp.service;

import com.example.shopapp.dto.response.ProductResponse;
import com.example.shopapp.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();

    Product getProduct(String id);

    void createProduct(Product product);

    void updateProduct(String id, Product product);

    void deleted(String id);

    Page<Product> getProductPage(Pageable pageable);
    Page<Product> search(String name , Pageable pageable);
    List<Product> findByStatusFalse();

}
