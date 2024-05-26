package com.example.shopapp.controller;

import com.example.shopapp.dto.request.OrderRequest;
import com.example.shopapp.dto.response.OrderResponse;
import com.example.shopapp.enums.Role;
import com.example.shopapp.model.Color;
import com.example.shopapp.service.iplm.OrderDetailServiceIplm;
import com.example.shopapp.service.iplm.OrderServiceIplm;
import com.example.shopapp.util.SessionUtil;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    SessionUtil session;
    OrderServiceIplm orderService;
    OrderDetailServiceIplm orderDetailService;
    String url = "/shop-app/orders/";

    @GetMapping
    public String index(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "2") Integer size,
            Model model) {
        if (session.get() == null) {
            return "redirect:/shop-app/admin/login";
        }
        initData(model, page, size);
        return "/orders/index";
    }

    @PostMapping("add")
    public String create(
            @Valid @ModelAttribute("order") OrderRequest request,
            BindingResult result,
            Model model) {
        if (session.get() == null) {
            return "redirect:/shop-app/admin/login";
        }
        if (session.get().getRole().equals(Role.EMPLOYEE)) {
            return "redirect:/shop-app/orders";
        }
        if (result.hasErrors()) {
            initData(model, 0, 2);
            return "/orders/index";
        }
        request.setStaffUserName(session.get().getUserName());
        boolean check = orderService.create(request);
        if (!check) {
            model.addAttribute("order", request);
            model.addAttribute("url", url + "add");
            Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "createdAt"));
            model.addAttribute("orders", orderService.getOrders(pageable));
            model.addAttribute("error", "Khong thể tạo với số này");
            return "/orders/index";
        }
        return "redirect:/shop-app/orders";
    }

    @GetMapping("update-status")
    public String updateStatus(
            @RequestParam String id,
            @RequestParam Integer status) {
        if (session.get() == null) {
            return "redirect:/shop-app/admin/login";
        }
        if (session.get().getRole().equals(Role.EMPLOYEE)) {
            return "redirect:/shop-app/orders";
        }
        orderService.updateStatus(id, status);
        return "redirect:/shop-app/orders";
    }


    /// OrderDetail
    @GetMapping("orders-detail")
    public String getAllOrderDetail(
            @RequestParam("id") String id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "2") Integer size,
            Model model) {
        if (session.get() == null) {
            return "redirect:/shop-app/admin/login";
        }

        initData(model, page, size);
        //
        model.addAttribute("ordersDetail", orderDetailService.getOrdersDetailList(id));
        return "/orders/index";
    }

    private void initData(Model model, Integer page, Integer size) {
        model.addAttribute("order", OrderResponse.builder().build());
        model.addAttribute("url", url + "add");
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        model.addAttribute("orders", orderService.getOrders(pageable));
    }
}
