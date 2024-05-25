package com.example.shopapp.dto.response;

import com.example.shopapp.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StaffResponse {
    String id;
    String code;
    String name;
    String userName;
    String password;
    Role role;
    Boolean status ;
}


