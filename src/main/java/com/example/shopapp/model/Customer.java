package com.example.shopapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String code;
    @NotNull(message = "Tên không được để trống")
    @NotBlank(message = "Tên không được để trống")
    String name;
    @Column(name = "phone_number", length = 10, nullable = false, unique = true)
    @NotNull(message = "Số điện thoại không được để trống")
    @NotBlank(message = "Sô điện thoại không được để trống")
    @Size(min = 10, max = 10, message = "Số điện thoại 10 chữ số")
    String phoneNumber;
    Boolean status = false;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Order> orders;
}
