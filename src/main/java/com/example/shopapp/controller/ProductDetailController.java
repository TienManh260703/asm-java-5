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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("shop-app/products/products-detail")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductDetailController {
    SessionUtil session;
    ColorServiceIplm colorService;
    ProductServiceIplm productService;
    SizeServiceIplm sizeService;
    ProductDetailServiceIplm productDetailService;
    String url = "/shop-app/products/products-detail/";
    static String saveProductId = "";
    @GetMapping
    public String index(
            @RequestParam(value = "productIdParam", defaultValue = "") String productId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Model model) {
        initSelectBox(model);
        saveProductId = productId;
        ProductDetailRequest productDetail = new ProductDetailRequest();
        productDetail.setCode(GenCode.generatePRODUCT_DETAIL());
        model.addAttribute("productId", productId);
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("productsDetail", productDetailService.findAllByProductId(productId, PageRequest.of(page, size)));
        model.addAttribute("url", url + "add");
        return "/product-detail/index";
    }

    @PostMapping("add")
    public String create(
            @Valid @ModelAttribute("productDetail") ProductDetailRequest request, BindingResult result, Model model) {
        if (session.get() == null) {
            return "redirect:/shop-app/admin/login";
        }
        if (result.hasErrors()) {
            model.addAttribute("productDetail", request);
            model.addAttribute("productsDetail", productDetailService.findAllByProductId(saveProductId, PageRequest.of(0, 5)));
            return "/product-detail/index";
        }
        productDetailService.createProductDetail(request);
        return "redirect:/shop-app/products/products-detail?productIdParam=" + saveProductId;
    }

    @PostMapping("update")
    public String update(
            @RequestParam("id") String id,
            @Valid @ModelAttribute("productDetail") ProductDetailRequest request,
            BindingResult result,
            Model model) {
        if (session.get() == null) {
            return "redirect:/shop-app/admin/login";
        }
        if (result.hasErrors()) {
            model.addAttribute("productDetail", request);
            model.addAttribute("productsDetail", productDetailService.findAllByProductId(saveProductId, PageRequest.of(0, 5)));
            return "/product-detail/index";
        }
        productDetailService.updateProductDetail(id, request);
        return "redirect:/shop-app/products/products-detail?productIdParam=" + saveProductId;
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
        model.addAttribute("url", url + "update?id=" + id + "&productIdParam=" + saveProductId);
        model.addAttribute("productsDetail", productDetailService.findAllByProductId(saveProductId, PageRequest.of(page, size)));
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
        model.addAttribute("productsDetail", productDetailService.findAllByProductId(saveProductId, PageRequest.of(page, size)));
        System.err.println("sssssssss :" + saveProductId);
        return "/product-detail/index";
    }

    @GetMapping("update-status")
    public String delete(@RequestParam String id) {
        if (session.get() == null) {
            return "redirect:/shop-app/admin/login";
        }
        productDetailService.deleted(id);
        return "redirect:/shop-app/products/products-detail?productIdParam=" + saveProductId;
    }

    @GetMapping("search")
    public String search(
            @RequestParam(value = "min" , defaultValue = "0") Float min,
            @RequestParam(value = "max", defaultValue = "999999999999999999999") Float max,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Model model) {

        Page<ProductDetailResponse> responses = productDetailService
                .findByPriceRange(
                        saveProductId, min, max, PageRequest.of(page, size));
        System.err.println("sie :"+responses.getTotalPages());
        if (responses.isEmpty()) {
            responses = productDetailService.findAllByProductId(saveProductId, PageRequest.of(0, size));
        }
        initSelectBox(model);
        ProductDetailRequest productDetail = new ProductDetailRequest();
        productDetail.setCode(GenCode.generatePRODUCT_DETAIL());
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("productsDetail", responses);
        model.addAttribute("url", url + "add");
        return "/product-detail/index";
    }

    // Bỏ
    @GetMapping("-")
    public String getProductsDetailByProductId(
            @RequestParam(value = "productIdParam") String productId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Model model) {
        saveProductId = productId;
        initSelectBox(model);
        ProductDetailRequest productDetail = new ProductDetailRequest();
        productDetail.setCode(GenCode.generatePRODUCT_DETAIL());
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("productsDetail", productDetailService.findAllByProductId(productId, PageRequest.of(page, size)));
        model.addAttribute("url", url + "add");
        return "/product-detail/index";
    }

    /// Bỏ
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

    ///
    private void initSelectBox(Model model) {
        model.addAttribute("productId", saveProductId);
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
