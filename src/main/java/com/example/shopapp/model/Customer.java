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
@Table(name = "customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String code;
    String name;
    @Column(name = "phone_number",length = 10,nullable = false, unique = true)
    String phoneNumber;
    Boolean status=false;
    @OneToMany( mappedBy = "customer" ,fetch =  FetchType.LAZY, cascade = CascadeType.ALL)
    List<Order> orders;
}
