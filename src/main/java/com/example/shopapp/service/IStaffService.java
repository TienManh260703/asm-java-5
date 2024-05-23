package com.example.shopapp.service;

import com.example.shopapp.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IStaffService {
    boolean existsByUserName(String userName);

    boolean existsByUserNameAndIdNot(String name, String id);

    Page<Staff> getStaffs(Pageable pageable);

    Page<Staff> search(String key,  Pageable pageable);

    Optional<Staff> findByIdAndStatusTrue(String id);
    Staff getStaff(String id);

    void create(Staff staff);

    void update(String id, Staff staff);
    void  deleted(String id);
    Staff findByUserName(String userName);
}
