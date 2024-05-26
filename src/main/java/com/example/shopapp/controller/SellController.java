package com.example.shopapp.controller;

import com.example.shopapp.dto.request.OrderRequest;
import com.example.shopapp.dto.response.OrderResponse;
import com.example.shopapp.model.ProductDetail;
import com.example.shopapp.service.*;
import com.example.shopapp.service.iplm.SellService;
import com.example.shopapp.util.SessionUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("shop-app/sells")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SellController {
    SessionUtil session;
    SellService sellService;
    IProductDetailService iProductDetailService;
    static String saveIdOrder = "";

    @GetMapping
    public String index(
            @RequestParam(value = "pageO", defaultValue = "0") Integer pageO,
            @RequestParam(value = "sizeO", defaultValue = "2") Integer sizeO,
            @RequestParam(value = "pageP", defaultValue = "0") Integer pageP,
            @RequestParam(value = "sizeP", defaultValue = "5") Integer sizeP,
            @RequestParam(value = "idOrder", defaultValue = "") String idOrder,

            Model model) {
        if (session.get() == null) {
            return "redirect:/shop-app/admin/login";
        }
        OrderResponse response = new OrderResponse();
        if (!saveIdOrder.equals("")) {
            response = sellService.getOrderById(saveIdOrder);
        }
        model.addAttribute("infoOrder", response == null ? new OrderResponse() : response);
        model.addAttribute("orders", sellService.findOrderByStatus(0, PageRequest.of(pageO, sizeO)));
        model.addAttribute("ordersDetail", sellService.findOrderByOrderId(saveIdOrder.equals("") ? idOrder : saveIdOrder));
        model.addAttribute("productDetail", iProductDetailService.findByDeletedFalseResponse(PageRequest.of(pageP, sizeP)));
        return "/sell/index";
    }

    @GetMapping("show-orders-detail")
    public String showOrderDetailById(
            @RequestParam(value = "pageO", defaultValue = "0") Integer pageO,
            @RequestParam(value = "sizeO", defaultValue = "2") Integer sizeO,
            @RequestParam(value = "idOrder", defaultValue = "") String idOrder,
            @RequestParam(value = "pageP", defaultValue = "0") Integer pageP,
            @RequestParam(value = "sizeP", defaultValue = "5") Integer sizeP,
            Model model) {
        saveIdOrder = idOrder.equals("") ? saveIdOrder : idOrder;
        OrderResponse response = sellService.getOrderById(saveIdOrder);

        model.addAttribute("infoOrder", response == null ? new OrderResponse() : response);
        model.addAttribute("orders", sellService.findOrderByStatus(0, PageRequest.of(pageO, sizeO)));
        model.addAttribute("ordersDetail", sellService.findOrderByOrderId(saveIdOrder));
        model.addAttribute("_idOrder", saveIdOrder);

        model.addAttribute("productDetail", iProductDetailService.findByDeletedFalseResponse(PageRequest.of(pageP, sizeP)));
        return "/sell/index";
    }

    @GetMapping("update-orders-detail")
    public String updateOrderDetail(
            @RequestParam(value = "quantity") Integer quantity,
            @RequestParam(value = "pageO", defaultValue = "0") Integer pageO,
            @RequestParam(value = "sizeO", defaultValue = "2") Integer sizeO,
            @RequestParam(value = "idOrder", defaultValue = "") String idOrder,
            @RequestParam(value = "orderDetailId", defaultValue = "") String orderDetailId,
            @RequestParam(value = "pageP", defaultValue = "0") Integer pageP,
            @RequestParam(value = "sizeP", defaultValue = "5") Integer sizeP,
            Model model) {
        model.addAttribute("infoOrder", new OrderRequest());
        sellService.updateOrderDetailQuantity(orderDetailId, quantity);
        model.addAttribute("orders", sellService.findOrderByStatus(0, PageRequest.of(pageO, sizeO)));
        model.addAttribute("ordersDetail", sellService.findOrderByOrderId(saveIdOrder));
        model.addAttribute("productDetail", iProductDetailService.findByDeletedFalseResponse(PageRequest.of(pageP, sizeP)));
        return "redirect:/shop-app/sells";
    }

    @GetMapping("add-order-detail")
    public String addOrderDetail(
            @RequestParam(value = "pageO", defaultValue = "0") Integer pageO,
            @RequestParam(value = "sizeO", defaultValue = "2") Integer sizeO,
//            @RequestParam(value = "idOrder", defaultValue = "") String idOrder,
            @RequestParam(value = "productDetailId", defaultValue = "") String productDetailId,
            @RequestParam(value = "pageP", defaultValue = "0") Integer pageP,
            @RequestParam(value = "sizeP", defaultValue = "5") Integer sizeP,
            Model model
    ) {
        model.addAttribute("infoOrder", new OrderRequest());
        model.addAttribute("orders", sellService.findOrderByStatus(0, PageRequest.of(pageO, sizeO)));
        model.addAttribute("ordersDetail", sellService.findOrderByOrderId(saveIdOrder));
        model.addAttribute("productDetail", iProductDetailService.findByDeletedFalseResponse(PageRequest.of(pageP, sizeP)));
        sellService.addOrderDetail(productDetailId, saveIdOrder);
        return "redirect:/shop-app/sells";
    }

    @PostMapping("pay")
    private String pay(
            @Valid @ModelAttribute("infoOrder") OrderRequest request,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("infoOrder", request);
            model.addAttribute("orders", sellService.findOrderByStatus(0, PageRequest.of(0, 2)));
            model.addAttribute("ordersDetail", sellService.findOrderByOrderId(saveIdOrder));
            model.addAttribute("productDetail", iProductDetailService.findByDeletedFalseResponse(PageRequest.of(0, 5)));
            return "/sell/index";
        }
        if (request.getMoneyReceived() < request.getTotalMoney()) {
            model.addAttribute("orders", sellService.findOrderByStatus(0, PageRequest.of(0, 2)));
            model.addAttribute("ordersDetail", sellService.findOrderByOrderId(saveIdOrder));
            model.addAttribute("productDetail", iProductDetailService.findByDeletedFalseResponse(PageRequest.of(0, 5)));
            model.addAttribute("message", "Tiền thanh toán phải lớn hơn hoặc bằng tổng tiền");
            model.addAttribute("infoOrder", request);
            return "/sell/index";

        }
        sellService.pay(request);
        saveIdOrder = "";
        return "redirect:/shop-app/sells";
    }

    @GetMapping("delete-order-detail")
    public String deleteOrderDetail(
            @RequestParam("orderDetailId") String orderDetailId,
            Model model
    ) {
        sellService.delete(orderDetailId);
        return "redirect:/shop-app/sells";
    }
}
