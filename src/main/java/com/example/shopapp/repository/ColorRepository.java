package com.example.shopapp.repository;

import com.example.shopapp.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, String> {
    boolean existsByName(String name);
}
