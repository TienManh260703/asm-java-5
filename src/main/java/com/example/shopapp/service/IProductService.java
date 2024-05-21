package com.example.shopapp.service;

import com.example.shopapp.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();

    Product getProduct(String id);

    void createProduct(Product product);

    void updateProduct(String id, Product product);
    void deleted(String id);



}
