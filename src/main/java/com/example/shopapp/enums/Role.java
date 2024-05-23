package com.example.shopapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Role {
    ADMIN("Quản lý"),
    EMPLOYEE("Nhân viên");
    private String description;
}
