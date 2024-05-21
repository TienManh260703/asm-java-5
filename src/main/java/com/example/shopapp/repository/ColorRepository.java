package com.example.shopapp.repository;

import com.example.shopapp.model.Color;
import com.example.shopapp.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, String> {
    boolean existsByName(String name);

    Page<Color> findAll(Pageable pageable);

    boolean existsByNameAndIdNot(String name, String id);

    Page<Color> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
