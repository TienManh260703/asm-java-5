package com.example.shopapp.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String code;
    String name;
    Boolean status =false;
    @OneToMany(mappedBy = "product" , fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    List<ProductDetail> productDetails;
}
