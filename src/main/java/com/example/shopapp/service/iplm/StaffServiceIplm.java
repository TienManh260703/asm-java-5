package com.example.shopapp.service.iplm;

import com.example.shopapp.enums.Role;
import com.example.shopapp.model.Staff;
import com.example.shopapp.repository.StaffRepository;
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
        return staffRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByUserNameAndIdNot(String name, String id) {
        return staffRepository.existsByUserNameAndIdNot(name, id);
    }

    @Override
    public Page<Staff> getStaffs(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }

    @Override
    public Page<Staff> search(String key, Pageable pageable) {
        return staffRepository.findByNameContainingIgnoreCase(key, pageable);
    }

    @Override
    public Optional<Staff> findByIdAndStatusTrue(String id) {
        return staffRepository.findByIdAndStatusTrue(id);
    }

    @Override
    public Staff getStaff(String id) {
        Staff staff = staffRepository.findById(id).get();

        return staff;
    }

    @Override
    public void create(Staff staff) {
        // PasswordEncoder
        staff.setRole(Role.EMPLOYEE);
        staffRepository.save(staff);
    }

    @Override
    public void update(String id, Staff staff) {
        Optional<Staff> staffOptional = staffRepository.findById(id);
        if (staffOptional.isPresent()) {
            Staff existingStaff = staffOptional.get();
            existingStaff.setName(staff.getName());
            existingStaff.setUserName(staff.getUserName());
            // PasswordEncoder ->
            existingStaff.setPassword(staff.getPassword());
//            existingStaff.setRole(Role.EMPLOYEE);
            staffRepository.save(existingStaff);
        }
    }

    @Override
    public void deleted(String id) {
        Optional<Staff> staffOptional = staffRepository.findById(id);
        if (staffOptional.isPresent()) {
            Staff existingStaff = staffOptional.get();
            existingStaff.setStatus(!existingStaff.getStatus());
            staffRepository.save(existingStaff);
        }
    }
}
