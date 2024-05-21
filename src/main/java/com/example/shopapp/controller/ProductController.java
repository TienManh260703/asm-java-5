package com.example.shopapp.controller;

import com.example.shopapp.model.Product;
import com.example.shopapp.service.iplm.ProductServiceIplm;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.example.shopapp.common.GenCode.generatePRODUCT;

@Controller
@RequestMapping("shop-app/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductServiceIplm productService;
    private static String url = "add";

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("url", "/shop-app/products/" + url);
        model.addAttribute("product", Product.builder().code(generatePRODUCT()).build());
        model.addAttribute("products", productService.getProducts());
        return "/products/index";
    }

    @PostMapping("add")
    public String createProduct(@Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("products", productService.getProducts());
            return "/products/index";
        }
        productService.createProduct(product);
        return "redirect:/shop-app/products";
    }

    @PostMapping("update")
    public String updateProduct(@RequestParam String id, @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("products", productService.getProducts());
            return "/products/index";
        }
        productService.updateProduct(id, product);
        url="add";
        return "redirect:/shop-app/products";
    }

    @GetMapping("view-update")
    public String viewUpdate(@RequestParam String id, Model model) {

        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        productService.updateProduct(id, product);
        model.addAttribute("products", productService.getProducts());
        url = "update";
        model.addAttribute("url", "/shop-app/products/" + url);
        return "/products/index";
    }

    @GetMapping("update-status")
    public String delete(@RequestParam String id) {
        productService.deleted(id);
        return "redirect:/shop-app/products";
    }
}
