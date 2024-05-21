package com.example.shopapp.repository;

import com.example.shopapp.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Page<Product> findAll(Pageable pageable);
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, String id);
}
