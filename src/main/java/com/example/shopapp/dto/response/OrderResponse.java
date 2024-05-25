package com.example.shopapp.dto.response;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String id;
    String staffId;
    String staffUserName;
    String customerId;
    String customerName;
    String phoneNumber;
    Float totalMoney;
    LocalDateTime createdAt;
    LocalDateTime dateOfPayment;
    Integer status ;
}
