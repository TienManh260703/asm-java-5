package com.example.shopapp.controller;

import com.example.shopapp.common.GenCode;
import com.example.shopapp.enums.EnumStatusProduct;
import com.example.shopapp.model.Color;
import com.example.shopapp.model.Product;
import com.example.shopapp.model.ProductDetail;
import com.example.shopapp.model.Size;
import com.example.shopapp.repository.ProductRepository;
import com.example.shopapp.service.iplm.ColorServiceIplm;
import com.example.shopapp.service.iplm.ProductDetailServiceIplm;
import com.example.shopapp.service.iplm.ProductServiceIplm;
import com.example.shopapp.service.iplm.SizeServiceIplm;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("shop-app/products/products-detail")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductDetailController {

    ColorServiceIplm colorService;
    ProductServiceIplm productService;
    SizeServiceIplm sizeService;
    ProductDetailServiceIplm productDetailService;
    String url = "/shop-app/products/products-detail/";

    @GetMapping("test")
    public String index() {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(Product.builder().id("62d43f69-1d7e-4408-bb8c-c859459cad43").build());
        productDetail.setColor(Color.builder().id("34e9d7a2-b770-4dbb-a07d-6e629984c947").build());
        productDetail.setSize(Size.builder().id("24f5193f-5cf9-4fa9-a0a7-2fe064cc91b1").build());
//        productDetail.setStatus(EnumStatusProduct.ALMOST_OUT_OF_STOCK);
        productDetail.setQuantity(10);
        productDetail.setPrice(1234f);
        productDetailService.createProductDetail(productDetail);
        return "/test/index";
    }

    @GetMapping
    public String index(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Model model) {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setCode(GenCode.generatePRODUCT_DETAIL());
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("productsDetail", productDetailService.getProductsDetail(PageRequest.of(page, size)));
        model.addAttribute("url", url + "add");
        return "/product-detail/index";
    }

    @PostMapping("add")
    public String create(@Valid ProductDetail request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productDetail", request);
            model.addAttribute("productsDetail", productDetailService.getProductsDetail(PageRequest.of(0, 5)));
            return "/product-detail/index";
        }
        productDetailService.createProductDetail(request);
        return "redirect:/shop-app/products/products-detail";
    }

    @GetMapping("view-update")
    public String viewUpdate(
            @RequestParam String id,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Model model) {
        System.err.println(productDetailService.getProductDetail(id).getProductName());
        model.addAttribute("productDetail", productDetailService.getProductDetail(id));
        model.addAttribute("productsDetail", productDetailService.getProductsDetail(PageRequest.of(page, size)));
        return "/product-detail/index";
    }

    @ModelAttribute("colors")
    public List<Color> getColors() {
        List<Color> colors = colorService.findByStatusFalse();
        return colors;
    }

    @ModelAttribute("products")
    public List<Product> getProducts() {
        List<Product> products = productService.findByStatusFalse();
        return products;
    }

    @ModelAttribute("sizes")
    public List<Size> getSizes() {
        List<Size> sizes = sizeService.findByStatusFalse();
        return sizes;
    }
}
