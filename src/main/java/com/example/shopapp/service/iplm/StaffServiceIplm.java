package com.example.shopapp.service.iplm;

import com.example.shopapp.model.Staff;
import com.example.shopapp.repository.StaffRepository;
import com.example.shopapp.service.IColorService;
import com.example.shopapp.service.IStaffService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffServiceIplm implements IStaffService {
    StaffRepository staffRepository;

    @Override
    public boolean existsByUserName(String userName) {
        return false;
    }

    @Override
    public boolean existsByUserNameAndIdNot(String name, String id) {
        return false;
    }

    @Override
    public Page<Staff> getStaffs(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Staff> search(String name, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Staff> findByIdAndStatusTrue(String id) {
        return Optional.empty();
    }
}
