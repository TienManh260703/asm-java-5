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
    String sizeId;
    Float sizeName;
    String colorId;
    String colorName;
    String productId;
    String productName;
    Integer quantity;
    Float price;
    String status;
    Boolean deleted;
}
