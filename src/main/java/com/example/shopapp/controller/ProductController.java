package com.example.shopapp.controller;

import com.example.shopapp.model.Product;
import com.example.shopapp.service.iplm.ProductServiceIplm;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private static String url = "/shop-app/products/";

    @GetMapping
    public String getAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Model model) {
        model.addAttribute("url", url + "add");
        model.addAttribute("product", Product.builder().code(generatePRODUCT()).build());
        model.addAttribute("products", productService.getProductPage(PageRequest.of(page, size)));
        return "/products/index";
    }

    @PostMapping("add")
    public String createProduct(
            @Valid Product product, BindingResult result,
            Model model) {
        model.addAttribute("id", product.getId());
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
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            model.addAttribute("id", product.getId());
            model.addAttribute("products", productService.getProducts());
            return "/products/index";
        }
        productService.updateProduct(id, product);
        model.addAttribute("id", product.getId());
        model.addAttribute("url", url + "add");
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
        productService.updateProduct(id, product);
        PageRequest pageRequest = PageRequest.of(page, size);
        model.addAttribute("products", productService.getProductPage(pageRequest));
        model.addAttribute("url", url + "update");
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
        productService.updateProduct(id, product);
        model.addAttribute("id", product.getId());
        model.addAttribute("products", productService.getProductPage(PageRequest.of(page,size)));
        model.addAttribute("isDetail", true);
        model.addAttribute("url", url + "update");
        return "/products/index";
    }

    @GetMapping("update-status")
    public String delete(@RequestParam String id) {
        productService.deleted(id);
        return "redirect:/shop-app/products";
    }
}
