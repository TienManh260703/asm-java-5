package com.example.shopapp.model;

import com.example.shopapp.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "staff")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String code;
    @NotBlank(message = "Tên không được để trống")
    String name;
    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min = 6 , max = 10, message = "Tên đăng nhập phải 6 ký tự -> 10 ký tự")
    @Column(name = "user_name")
    String userName;
    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6 , max = 10, message = "Mật khẩu đăng nhập phải 6 ký tự -> 10 ký tự")
    String password;
    Role role;
    Boolean status = false;
    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Order> orders;
}
