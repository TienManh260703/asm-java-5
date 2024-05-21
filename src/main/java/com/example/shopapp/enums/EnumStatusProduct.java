package com.example.shopapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum EnumStatusProduct {
    STOCKING("Còn hàng"),
    OUT_OF_STOCK("Hết hàng"),
    STOP_SELLING("Ngừng bán"),
    ALMOST_OUT_OF_STOCK("Sắp hết");
    private String description;
}
