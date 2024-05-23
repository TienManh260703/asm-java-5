package com.example.shopapp.service.iplm;

import com.example.shopapp.model.Customer;
import com.example.shopapp.repository.CustomerRepository;
import com.example.shopapp.service.IColorService;
import com.example.shopapp.service.ICustomerService;
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
public class CustomerServiceIplm implements ICustomerService {

    CustomerRepository customerRepository;

    @Override
    public Page<Customer> getCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getCustomer(String id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        }
        return new Customer();
    }

    @Override
    public Page<Customer> search(String key, Pageable pageable) {
        return customerRepository
                .findByNameContainingIgnoreCaseOrPhoneNumber(key, key, pageable);
    }

    @Override
    public boolean existsByPhoneNumberAndIdNot(String phoneNumber, String id) {
        return customerRepository.existsByPhoneNumberAndIdNot(phoneNumber,  id);
    }

    @Override
    public void create(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void update(String id, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setName(customer.getName());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            customerRepository.save(existingCustomer);
        }
    }

    @Override
    public void deleted(String id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setStatus(!existingCustomer.getStatus());
            customerRepository.save(existingCustomer);
        }
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return customerRepository.existsByPhoneNumber(phoneNumber);
    }
}
