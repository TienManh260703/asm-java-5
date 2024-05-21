package com.example.shopapp.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "color")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String code;
    String name;
    Boolean status =false;
    @OneToMany(mappedBy = "color" , fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    List<ProductDetail> productDetails;
}
