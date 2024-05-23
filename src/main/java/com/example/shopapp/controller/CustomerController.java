package com.example.shopapp.controller;

import com.example.shopapp.common.GenCode;
import com.example.shopapp.model.Customer;
import com.example.shopapp.service.iplm.CustomerServiceIplm;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("shop-app/customers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {
    CustomerServiceIplm customerService;
    String url = "/shop-app/customers/";

    @GetMapping
    public String index(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            Model model) {
        model.addAttribute("customer", Customer.builder().code(GenCode.generateCUSTOMER()).build());
        model.addAttribute("url", url + "add");
        model.addAttribute("customers", customerService.getCustomers(PageRequest.of(page, size)));
        return "/customers/index";
    }

    @PostMapping("add")
    public String create(
            @Valid Customer customer,
            BindingResult result,
            Model model) {
        Boolean checkPhone = customerService.existsByPhoneNumber(customer.getPhoneNumber());
        if (result.hasErrors() || checkPhone) {
            if (checkPhone) {
                model.addAttribute("message", "Số điện thoại đã tồn tại trong hệ thống");
            }
            model.addAttribute("customer", customer);
            model.addAttribute("customers", customerService.getCustomers(PageRequest.of(0, 5)));
            return "/customers/index";
        }
        customerService.create(customer);
        return "redirect:/shop-app/customers";
    }

    @PostMapping("update")
    public String update(
            @RequestParam String id,
            @Valid Customer customer,
            BindingResult result,
            Model model) {
        Boolean checkPhone = customerService.existsByPhoneNumberAndIdNot(customer.getPhoneNumber(), id);
        if (result.hasErrors() || checkPhone) {
            if (checkPhone) {
                model.addAttribute("message", "Số điện thoại đã tồn tại trong hệ thống");
            }
            model.addAttribute("customer", customer);
            model.addAttribute("customers", customerService.getCustomers(PageRequest.of(0, 5)));
            return "/customers/index";
        }
        customerService.update(id, customer);
        return "redirect:/shop-app/customers";
    }

    @GetMapping("view-update")
    public String viewUpdate(
            @RequestParam String id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            Model model) {
        model.addAttribute("customer", customerService.getCustomer(id));
        model.addAttribute("url", url + "update");
        model.addAttribute("customers", customerService.getCustomers(PageRequest.of(page, size)));
        model.addAttribute("isEdit", true);
        return "/customers/index";
    }

    @GetMapping("detail")
    public String detail(
            @RequestParam String id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            Model model) {
        Customer customer = customerService.getCustomer(id);
        model.addAttribute("customer", customer);
        model.addAttribute("id", customer.getId());
        model.addAttribute("customers", customerService.getCustomers(PageRequest.of(page, size)));
        model.addAttribute("isDetail", true);
        return "/customers/index";
    }

    @GetMapping("update-status")
    public String deleted(@RequestParam String id) {
        customerService.deleted(id);
        return "redirect:/shop-app/customers";
    }

    @GetMapping("search")
    public String search(
            @RequestParam String id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            Model model) {
        model.addAttribute("url", url + "add");
        model.addAttribute("customer", Customer.builder().code(GenCode.generateCUSTOMER()).build());
        model.addAttribute("customers", customerService.search(id, PageRequest.of(page, size)));
        return "/customers/index";
    }
}
