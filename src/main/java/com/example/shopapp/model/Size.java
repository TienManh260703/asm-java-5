package com.example.shopapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "size")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String code;
    @NotNull(message = "Tên không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Tên size hơn >=0.0")
    private Float name;
    Boolean status =false;
    @OneToMany(mappedBy = "size" , fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    List<ProductDetail> productDetails;
}
