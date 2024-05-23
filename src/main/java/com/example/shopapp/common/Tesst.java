package com.example.shopapp.common;

import com.example.shopapp.model.Customer;
import com.example.shopapp.repository.ProductDetailRepository;
import com.example.shopapp.service.iplm.CustomerServiceIplm;
import com.example.shopapp.service.iplm.ProductDetailServiceIplm;
import com.example.shopapp.service.iplm.ProductServiceIplm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Tesst {
    @Autowired
    ProductDetailServiceIplm productDetailServiceIplm;
    @Autowired
    CustomerServiceIplm customerServiceIplm;

    @ResponseBody
    @GetMapping("/getProductPriceRange")
    public ResponseEntity<?> getProductPriceRange(Model model) {
        return ResponseEntity.ok().body(
                productDetailServiceIplm.findByProductIdAndPriceRange("3f1a71b9-ec44-4361-a76f-6b9b53eef4bc", 0, 346).size()
        );
    }

    @GetMapping("api-add")
    public ResponseEntity<?> addCustomer() {
        Customer customer = Customer.builder()
                .code(GenCode.generateCUSTOMER())
                .name("Nguyễn Phú Thứ")
                .phoneNumber("0868819930")
                .status(false)
                .build();
        customerServiceIplm.create(customer);
        return ResponseEntity.ok().body(customer);
    }
}
