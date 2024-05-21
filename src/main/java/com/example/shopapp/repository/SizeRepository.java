package com.example.shopapp.repository;

import com.example.shopapp.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, String> {
    boolean existsByName(String name);
}
