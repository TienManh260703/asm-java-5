package com.example.shopapp.service;

import com.example.shopapp.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService {
    Page<Customer> getCustomers(Pageable pageable);

    Customer getCustomer(String id);

    Customer findByPhoneNumber(String phone);

    Page<Customer> search(String key, Pageable pageable);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, String id);

    void create(Customer customer);

    void update(String id, Customer customer);

    void deleted(String id);

    boolean existsByPhoneNumber(String phoneNumber);
}
