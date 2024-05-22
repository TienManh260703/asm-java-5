package com.example.shopapp.repository;

import com.example.shopapp.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {
    boolean existsByUserName(String userName);

    boolean existsByUserNameAndIdNot(String name, String id);

    Page<Staff> findAll(Pageable pageable);

    Page<Staff> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Optional<Staff> findByIdAndStatusTrue(String id);
}
