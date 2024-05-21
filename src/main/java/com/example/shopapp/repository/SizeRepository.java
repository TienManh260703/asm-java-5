package com.example.shopapp.repository;

import com.example.shopapp.model.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, String> {
    boolean existsByName(Float name);
    boolean existsByNameAndIdNot(Float name, String id);
    Page<Size> findAll(Pageable pageable);
    Page<Size> findSizeByName(Float name , Pageable pageable);
}
