package com.example.shopapp.controller;

import com.example.shopapp.model.Product;
import com.example.shopapp.service.iplm.ProductServiceIplm;
import com.example.shopapp.util.SessionUtil;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    SessionUtil session;
    ProductServiceIplm productService;
    static String URL = "/shop-app/products/";

    @GetMapping
    public String getAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Model model) {
        model.addAttribute("url", URL + "add");
        model.addAttribute("product", Product.builder().code(generatePRODUCT()).build());
        model.addAttribute("products", productService.getProductPage(PageRequest.of(page, size)));
        return "/products/index";
    }

    @PostMapping("add")
    public String createProduct(
            @Valid Product product, BindingResult result,
            Model model) {
        if (session.get() == null) {
            return "redirect:/shop-app/admin/login";
        }
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("products", productService.getProductPage(PageRequest.of(0, 5)));
            return "/products/index";
        }

        productService.createProduct(product);
        return "redirect:/shop-app/products";
    }

    @PostMapping("update")
    public String updateProduct(@RequestParam String id, @Valid Product product, BindingResult result, Model model) {
        if (session.get() == null) {
            return "redirect:/shop-app/admin/login";
        }
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("id", product.getId());
            model.addAttribute("products", productService.getProducts());
            return "/products/index";
        }
        productService.updateProduct(id, product);
        model.addAttribute("id", product.getId());
        model.addAttribute("url", URL + "add");
        return "redirect:/shop-app/products";
    }

    @GetMapping("view-update")
    public String viewUpdate(
            @RequestParam String id,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size
            , Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("id", product.getId());
        model.addAttribute("product", product);
        PageRequest pageRequest = PageRequest.of(page, size);
        model.addAttribute("products", productService.getProductPage(pageRequest));
        model.addAttribute("url", URL + "update");
        model.addAttribute("isEdit", true);
        return "/products/index";
    }

    @GetMapping("detail")
    public String detail(@RequestParam String id,
                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                         @RequestParam(value = "size", defaultValue = "5") Integer size,
                         Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);

        model.addAttribute("id", product.getId());

        model.addAttribute("products", productService.getProductPage(PageRequest.of(page, size)));
        model.addAttribute("isDetail", true);
        model.addAttribute("url", URL + "update");
        return "/products/index";
    }

    @GetMapping("update-status")
    public String delete(@RequestParam String id) {
        if (session.get() == null) {
            return "redirect:/shop-app/admin/login";
        }
        productService.deleted(id);
        return "redirect:/shop-app/products";
    }

    @GetMapping("search")
    public String search(
            @RequestParam String id,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Model model) {

        model.addAttribute("url", URL + "add");
        model.addAttribute("product", Product.builder().code(generatePRODUCT()).build());
        Page<Product> products = productService.search(id, PageRequest.of(page, size));
        if (products.isEmpty()) {
            products = productService.getProductPage(PageRequest.of(page, size));
        }
        model.addAttribute("products", products);
        return "/products/index";
    }
}
