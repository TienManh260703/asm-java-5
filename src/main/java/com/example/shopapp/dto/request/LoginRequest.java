package com.example.shopapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {
    @NotBlank(message = "Tên đăng nhập không được đẻ trống")
    String username;
    @NotBlank(message = "Tên đăng nhập không được đẻ trống")
    String password;
    Boolean save;
}
