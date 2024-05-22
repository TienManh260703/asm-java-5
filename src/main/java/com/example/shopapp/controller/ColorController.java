package com.example.shopapp.controller;

import com.example.shopapp.common.GenCode;
import com.example.shopapp.model.Color;
import com.example.shopapp.service.iplm.ColorServiceIplm;
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

import static com.example.shopapp.common.GenCode.generateCOLOR;

@Controller
@RequestMapping("shop-app/colors")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ColorController {
    ColorServiceIplm colorService;
    String url = "/shop-app/colors/";

    @GetMapping
    public String index(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            Model model) {
        model.addAttribute("color", Color.builder().code(generateCOLOR()).build());
        model.addAttribute("url", url + "add");
        model.addAttribute("colors", colorService.getColorPage(PageRequest.of(page, size)));
        return "/colors/index";
    }

    @PostMapping("add")
    public String create(
            @Valid Color color, BindingResult result, Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("color", color);
            model.addAttribute("colors", colorService.getColorPage(PageRequest.of(0, 5)));
            return "/colors/index";
        }
        colorService.create(color);
        return "redirect:/shop-app/colors";
    }

    @PostMapping("update")
    public String update(
            @RequestParam String id,
            @Valid Color color,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("color", color);
            model.addAttribute("colors", colorService.getColorPage(PageRequest.of(0, 5)));
            return "/colors/index";
        }
        colorService.update( id,color);
        return "redirect:/shop-app/colors";
    }

    @GetMapping("view-update")
    public String updateColor(
            @RequestParam String id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            Model model
    ) {
        Color color = colorService.getColor(id);
        model.addAttribute("color", color);
        model.addAttribute("id", color.getId());
        model.addAttribute("colors", colorService.getColorPage(PageRequest.of(page, size)));
        model.addAttribute("url", url + "update");
        model.addAttribute("isEdit", true);
        return "/colors/index";
    }

    @GetMapping("update-status")
    public String deleted(@RequestParam String id) {
        colorService.deleted(id);
        return "redirect:/shop-app/colors";
    }

    @GetMapping("detail")
    public String detail(
            @RequestParam String id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            Model model
    ) {
        Color color = colorService.getColor(id);
        model.addAttribute("color", color);
        model.addAttribute("id", color.getId());
        model.addAttribute("colors", colorService.getColorPage(PageRequest.of(page, size)));
        model.addAttribute("isDetail", true);
        return "/colors/index";
    }

    @GetMapping("search")
    public String search(
            @RequestParam String id,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Model model) {
        model.addAttribute("color", Color.builder().code(GenCode.generateCOLOR()).build());
        model.addAttribute("colors", colorService.search(id, PageRequest.of(page, size)));
        return "/colors/index";
    }

}
