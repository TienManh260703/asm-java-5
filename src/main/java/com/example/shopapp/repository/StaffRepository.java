package com.example.shopapp.repository;

import com.example.shopapp.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {
    boolean existsByUserName(String userName);
}
