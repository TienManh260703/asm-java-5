package com.example.shopapp.controller;

import com.example.shopapp.dto.request.LoginRequest;
import com.example.shopapp.model.Staff;
import com.example.shopapp.service.IStaffService;
import com.example.shopapp.util.SessionUtil;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("shop-app/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginController {
    SessionUtil session;
    IStaffService iStaffService;

    @GetMapping("login")
    public String index(Model model) {
        model.addAttribute("account", new LoginRequest());
        return "/login/index";
    }

    @PostMapping("login")
    public String login(
            @Valid @ModelAttribute("account") LoginRequest request,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("account", request);
            return "/login/index";
        }

        Staff existingStaff = iStaffService.login(request.getUsername(), request.getPassword());
        if (existingStaff == null) {
            model.addAttribute("message", "Tài khoản không tồn tại hoặc bị khóa");
            return "/login/index";
        }
        session.set(existingStaff);
        return "redirect:/shop-app/sells";
    }

    @GetMapping("logout")
    public String logout(Model model){
        session.remove("account");
        return "redirect:/shop-app/admin/login";
    }
}
