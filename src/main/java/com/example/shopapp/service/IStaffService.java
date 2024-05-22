package com.example.shopapp.service;

import com.example.shopapp.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IStaffService {
    boolean existsByUserName(String userName);

    boolean existsByUserNameAndIdNot(String name, String id);

    Page<Staff> getStaffs(Pageable pageable);

    Page<Staff> search(String name, Pageable pageable);

    Optional<Staff> findByIdAndStatusTrue(String id);
}
