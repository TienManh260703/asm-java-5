package com.example.shopapp.controller;

import com.example.shopapp.dto.request.OrderRequest;
import com.example.shopapp.dto.response.OrderResponse;
import com.example.shopapp.model.Color;
import com.example.shopapp.service.iplm.OrderServiceIplm;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.example.shopapp.common.GenCode.generateCOLOR;

@Controller
@RequestMapping("shop-app/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderServiceIplm orderService;
    String url = "/shop-app/orders/";

    @GetMapping
    public String index(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "2") Integer size,
            Model model) {
        model.addAttribute("order", OrderResponse.builder().build());
        model.addAttribute("url", url + "add");
        model.addAttribute("orders", orderService.getOrders(PageRequest.of(page, size)));
        return "/orders/index";
    }

    @PostMapping("add")
    public String create(
            @Valid @ModelAttribute("order") OrderRequest request,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("order", request);
            model.addAttribute("url", url + "add");
            model.addAttribute("orders", orderService.getOrders(PageRequest.of(0, 2)));
            return "/orders/index";
        }
        orderService.create(request);
        return "redirect:/shop-app/orders";
    }

    @GetMapping("update-status")
    public String updateStatus(
            @RequestParam String id,
            @RequestParam Integer status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "2") Integer size) {
        orderService.updateStatus(id, status);
        return "redirect:/shop-app/orders";
    }
}
