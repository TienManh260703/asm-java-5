package com.example.shopapp.controller;

import com.example.shopapp.common.GenCode;
import com.example.shopapp.model.Size;
import com.example.shopapp.service.iplm.SizeServiceIplm;
import com.example.shopapp.util.SessionUtil;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("shop-app/sizes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SizeController {
    SessionUtil session;
    SizeServiceIplm sizeService;
    String url = "/shop-app/sizes/";

    @GetMapping
    public String index(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Model model) {
        model.addAttribute("size", Size.builder().code(GenCode.generateSIZE()).build());
        model.addAttribute("url", url + "add");
        model.addAttribute("sizes", sizeService.getSizes(PageRequest.of(page, size)));
        return "/sizes/index";
    }

    @PostMapping("add")
    public String create(@Valid Size size, BindingResult result, Model model) {
        if (result.hasErrors() || sizeService.existsByName(size.getName())) {
            model.addAttribute("message", "Tên đã tồn tại !");
            model.addAttribute("size", size);
            model.addAttribute("sizes", sizeService.getSizes(PageRequest.of(0, 5)));
            return "/sizes/index";
        }

        sizeService.create(size);
        return "redirect:/shop-app/sizes";
    }

    @GetMapping("view-update")
    public String viewUpdate(@RequestParam String id, @RequestParam(defaultValue = "0") Integer page,
                             @RequestParam(defaultValue = "5") Integer size, Model model) {
        Size sizeO = sizeService.getSize(id);
        model.addAttribute("url", url + "update");
        model.addAttribute("isEdit", true);
        model.addAttribute("size", sizeO);
        model.addAttribute("sizes", sizeService.getSizes(PageRequest.of(page, size)));
        return "/sizes/index";
    }

    @PostMapping("update")
    public String update(
            @RequestParam String id,
            @Valid Size size,
            BindingResult result,
            Model model) {
        if (result.hasErrors() || sizeService.existsByNameAndIdNot(size.getName(), id)) {
            model.addAttribute("message", "Tên đã tồn tại ở 1 size khác !");
            model.addAttribute("size", size);
            model.addAttribute("sizes", sizeService.getSizes(PageRequest.of(0, 5)));
            return "/sizes/index";
        }
        sizeService.update(id, size);
        return "redirect:/shop-app/sizes";
    }

    @GetMapping("detail")
    public String detail(
            @RequestParam String id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            Model model) {
        Size existingSize = sizeService.getSize(id);
        model.addAttribute("url", url + "update");

        model.addAttribute("isDetail", true);
        model.addAttribute("size", existingSize);
        model.addAttribute("id", existingSize.getId());
        model.addAttribute("sizes", sizeService.getSizes(PageRequest.of(page, size)));
        return "/sizes/index";
    }

    @GetMapping("update-status")
    public String deleted(@RequestParam String id) {
        sizeService.deleted(id);
        return "redirect:/shop-app/sizes";
    }

    @GetMapping("search")
    public String search(@RequestParam(value = "name", defaultValue = "0") String name, @RequestParam(value = "page", defaultValue = "0") Integer page,
                         @RequestParam(value = "size", defaultValue = "5") Integer size, Model model) {
        model.addAttribute("size", Size.builder().code(GenCode.generateSIZE()).build());
        model.addAttribute("sizes", sizeService.search(Float.parseFloat(name), PageRequest.of(page, size)));
        return "/sizes/index";
    }
}
