package com.example.shopapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EnumStatusOrder {
    PENDING("Chưa thanh toán"),
    SUCCESS("Đã thanh toán"),
    CANCEL("Hủy");
   private String description;
}
