package com.example.shopapp.model;

import com.example.shopapp.enums.EnumStatusProduct;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
@Table(name = "product_detail")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String code;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id", nullable = false)
    Size size;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id", nullable = false)
    Color color;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    Product product;
    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng phải >= 0")
    Integer quantity;
    @NotNull(message = "Giá không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá hơn >=0.0")
    Float price;
    //    EnumStatusProduct status;
    Boolean deleted = false;
}
