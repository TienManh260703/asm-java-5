package com.example.shopapp.controller;

import com.example.shopapp.common.GenCode;
import com.example.shopapp.enums.Role;
import com.example.shopapp.model.Color;
import com.example.shopapp.model.Staff;
import com.example.shopapp.service.iplm.StaffServiceIplm;
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
        if (staffService.existsByUserName(staff.getUserName())) {
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

    @PostMapping("add")
    public String create(@Valid Staff staff, BindingResult result, Model model) {

        if (result.hasErrors() || staffService.existsByUserName(staff.getUserName())) {
            if (staffService.existsByUserName(staff.getUserName())) {
                model.addAttribute("message", "Tên đăng nhập đã tồn tại");
            }
            model.addAttribute("staff", Staff.builder().code(GenCode.generateSTAFF()).build());
            model.addAttribute("staffs", staffService.getStaffs(PageRequest.of(0, 5)));
            model.addAttribute("url", url + "add");
            return "/staffs/index";
        }
        staffService.create(staff);
        return "redirect:/shop-app/staffs";
    }

    @PostMapping("update")
    public String update(
            @RequestParam String id,
            @Valid Staff staff,
            BindingResult result,
            Model model) {

        if (result.hasErrors() || staffService.existsByUserNameAndIdNot(staff.getUserName(), id)) {
            if (staffService.existsByUserName(staff.getUserName())) {
                model.addAttribute("message", "Tên đăng nhập đã tồn tại");
            }
            model.addAttribute("staff", Staff.builder().code(GenCode.generateSTAFF()).build());
            model.addAttribute("staffs", staffService.getStaffs(PageRequest.of(0, 5)));
            model.addAttribute("url", url + "update");
            return "/staffs/index";
        }
        model.addAttribute("url", url + "add");
        staffService.update(id, staff);
        return "redirect:/shop-app/staffs";
    }

    @GetMapping("view-update")
    private String viewUpdate(
            @RequestParam String id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            Model model) {
        Staff staff = staffService.getStaff(id);
        model.addAttribute("staff", staff);
        model.addAttribute("id", staff.getId());
        model.addAttribute("staffs", staffService.getStaffs(PageRequest.of(page, size)));
        model.addAttribute("isEdit", true);
        model.addAttribute("url", url + "update");
        return "/staffs/index";
    }

    @GetMapping("detail")
    private String detail(
            @RequestParam String id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            Model model) {
        Staff staff = staffService.getStaff(id);
        model.addAttribute("staff", staff);
        model.addAttribute("id", staff.getId());
        model.addAttribute("staffs", staffService.getStaffs(PageRequest.of(page, size)));
        model.addAttribute("isDetail", true);
        return "/staffs/index";
    }

    @GetMapping("search")
    public String search (
            @RequestParam String id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            Model model

    ){
        Page<Staff> staffs = staffService.search(id, PageRequest.of(page, size));
        if(staffs.isEmpty()){
            staffs= staffService.getStaffs(PageRequest.of(page, size));
        }
        model.addAttribute("staff", Staff.builder().code(GenCode.generateSTAFF()).build());
        model.addAttribute("staffs", staffs);
        model.addAttribute("url", url + "add");
        return "/staffs/index";
    }
    @GetMapping("update-status")
    private String deleted(@RequestParam String id) {
        staffService.deleted(id);
        return "redirect:/shop-app/staffs";
    }

}
