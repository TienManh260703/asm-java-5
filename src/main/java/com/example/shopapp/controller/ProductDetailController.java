package com.example.shopapp.controller;

import com.example.shopapp.common.GenCode;
import com.example.shopapp.dto.request.ProductDetailRequest;
import com.example.shopapp.dto.response.ProductDetailResponse;
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

//    @GetMapping("test")
//    public String index() {
//        ProductDetailRequest productDetail = new ProductDetailRequest();
//        productDetail.setProduct(Product.builder().id("62d43f69-1d7e-4408-bb8c-c859459cad43").build());
//        productDetail.setColor(Color.builder().id("34e9d7a2-b770-4dbb-a07d-6e629984c947").build());
//        productDetail.setSize(Size.builder().id("24f5193f-5cf9-4fa9-a0a7-2fe064cc91b1").build());
////        productDetail.setStatus(EnumStatusProduct.ALMOST_OUT_OF_STOCK);
//        productDetail.setQuantity(10);
//        productDetail.setPrice(1234f);
//        productDetailService.createProductDetail(productDetail);
//        return "/test/index";
//    }

    @GetMapping
    public String index(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Model model) {
        initSelectBox(model);
        ProductDetailRequest productDetail = new ProductDetailRequest();
        productDetail.setCode(GenCode.generatePRODUCT_DETAIL());
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("productsDetail", productDetailService.getProductsDetail(PageRequest.of(page, size)));
        model.addAttribute("url", url + "add");
        return "/product-detail/index";
    }

    @PostMapping("add")
    public String create(@Valid @ModelAttribute("productDetail") ProductDetailRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productDetail", request);
            model.addAttribute("productsDetail", productDetailService.getProductsDetail(PageRequest.of(0, 5)));
            return "/product-detail/index";
        }
        productDetailService.createProductDetail(request);
        return "redirect:/shop-app/products/products-detail";
    }

    @PostMapping("update")
    public String update(
            @RequestParam("id") String id,
            @Valid @ModelAttribute("productDetail") ProductDetailRequest request,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productDetail", request);
            model.addAttribute("productsDetail", productDetailService.getProductsDetail(PageRequest.of(0, 5)));
            return "/product-detail/index";
        }
        productDetailService.updateProductDetail(id, request);
        return "redirect:/shop-app/products/products-detail";
    }

    @GetMapping("view-update")
    public String viewUpdate(
            @RequestParam String id,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Model model) {
        initSelectBox(model);
        ProductDetailResponse response = productDetailService.getProductDetail(id);
        selectOption(response, model);
        model.addAttribute("isEdit", true);
        model.addAttribute("productDetail", response);
        model.addAttribute("id", id);
        model.addAttribute("url", url + "update?id="+id);
        model.addAttribute("productsDetail", productDetailService.getProductsDetail(PageRequest.of(page, size)));
        return "/product-detail/index";
    }

    @GetMapping("detail")
    public String detail(
            @RequestParam String id,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Model model) {
        initSelectBox(model);
        ProductDetailResponse response = productDetailService.getProductDetail(id);
        selectOption(response, model);
        model.addAttribute("isDetail", true);
        model.addAttribute("productDetail", response);
        model.addAttribute("url", url + "update");
        model.addAttribute("productsDetail", productDetailService.getProductsDetail(PageRequest.of(page, size)));
        return "/product-detail/index";
    }

    @GetMapping("update-status")
    public String delete(@RequestParam String id) {
        productDetailService.deleted(id);
        return "redirect:/shop-app/products/products-detail";
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

    private void initSelectBox(Model model) {
        model.addAttribute("products", productService.findByStatusFalse());
        model.addAttribute("colors", colorService.findByStatusFalse());
        model.addAttribute("sizes", sizeService.findByStatusFalse());
    }

    private void selectOption(ProductDetailResponse response, Model model) {
        model.addAttribute("pId", response.getProductId());
        model.addAttribute("cId", response.getColorId());
        model.addAttribute("sId", response.getSizeId());
    }
}
