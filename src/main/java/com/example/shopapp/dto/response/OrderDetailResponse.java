package com.example.shopapp.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponse {
    String id;
    String orderId;
    String productDetailId;
    String productDetailName;
    Integer quantity;
    Float price;
    Float totalMoney;
    Boolean status;

    public Float getTotalMoney() {
        totalMoney = price * quantity;
        return totalMoney;
    }
}
