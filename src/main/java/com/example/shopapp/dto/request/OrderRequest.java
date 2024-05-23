package com.example.shopapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    String id;
    String staffId;
    @NotBlank(message = "Tên nhân viên chưa có")
    String staffUserName;
    String customerId;
//    @NotBlank(message = "Tên khách hàng chưa có")
    String customerName;
    @NotBlank(message = "Tên khách hàng chưa có")
    String phoneNumber;
    LocalDateTime createdAt;
    LocalDateTime dateOfPayment;
    Float totalMoney;
    Integer status ;
}
