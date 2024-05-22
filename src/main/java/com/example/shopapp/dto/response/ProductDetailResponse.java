package com.example.shopapp.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailResponse {
    String id;
    String code ;
    String name;
    Float sizeName;
    String colorName;
    String productName;
    Integer quantity;
    Float price;
    String status;
    Boolean deleted;
}
