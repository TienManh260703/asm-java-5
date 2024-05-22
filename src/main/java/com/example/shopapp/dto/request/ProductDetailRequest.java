package com.example.shopapp.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetailRequest {
    String id ;
    String code;
    String sizeId;
    String colorId;
    String productId;
    Integer quantity;
    Float price;
}
