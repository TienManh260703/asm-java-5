package com.example.shopapp.controller;

import com.example.shopapp.model.ProductDetail;
import com.example.shopapp.service.IOrderDetailService;
import com.example.shopapp.service.IOrderService;
import com.example.shopapp.service.IProductDetailService;
import com.example.shopapp.service.IStaffService;
import com.example.shopapp.util.SessionUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("shop-app/sells")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SellController {
    SessionUtil session;
    IProductDetailService productDetailService;
    IOrderService orderService;
    IOrderDetailService orderDetailService;
    IStaffService staffService;

    @GetMapping
    public String index() {
        return "/sell/index";
    }
}
