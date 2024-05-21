package com.example.shopapp.service.iplm;

import com.example.shopapp.model.Product;
import com.example.shopapp.repository.ProductRepository;
import com.example.shopapp.service.IProductService;
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
public class ProductServiceIplm implements IProductService {

    ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(String id) {
        return productRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Product not found");
        });
    }

    @Override
    public void createProduct(Product product) {
        if(productRepository.existsByName(product.getName().trim())){
            throw  new RuntimeException("Product name already exists !");
        }
        productRepository.save(product);
    }

    @Override
    public void updateProduct(String id, Product product) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Product not found");
        });
        if(productRepository.existsByNameAndIdNot(product.getName(),  id)){
            throw  new RuntimeException("Product name already exists !");
        }
        existingProduct.setName(product.getName());
        productRepository.save(existingProduct);
    }

    @Override
    public void deleted(String id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setStatus(!product.getStatus());
            productRepository.save(product);
        }
    }

    @Override
    public Page<Product> getProductPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
