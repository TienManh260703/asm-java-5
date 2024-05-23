package com.example.shopapp.repository;

import com.example.shopapp.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, String id);

    Page<Customer> findAll(Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR c.phoneNumber = :phoneNumber")
    Page<Customer> findByNameContainingIgnoreCaseOrPhoneNumber(@Param("keyword") String keyword, @Param("phoneNumber") String phoneNumber, Pageable pageable);
}
