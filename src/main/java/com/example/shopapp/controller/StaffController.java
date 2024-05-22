package com.example.shopapp.controller;

import com.example.shopapp.common.GenCode;
import com.example.shopapp.enums.Role;
import com.example.shopapp.model.Staff;
import com.example.shopapp.service.iplm.StaffServiceIplm;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("shop-app/staffs")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffController {

    StaffServiceIplm staffService;
    String url = "/shop-app/staffs/";

    @GetMapping("fake")
    public String fake(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Model model) {
        Staff staff = Staff.builder()
                .code(GenCode.generateSTAFF())
                .name("Trương Yến Vy")
                .userName("vy19924")
                .password("123456789")
                .role(Role.EMPLOYEE)
                .build();
    if(staffService.existsByUserName(staff.getUserName())) {
          return "/test/index";
    }
      staffService.create(staff);

        return "/test/index";
    }

    @GetMapping
    public String index(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            Model model) {
        model.addAttribute("staff", Staff.builder().code(GenCode.generateSTAFF()).build());
        model.addAttribute("staffs", staffService.getStaffs(PageRequest.of(page, size)));
        model.addAttribute("url", url + "add");

        return "/staffs/index";
    }
}
