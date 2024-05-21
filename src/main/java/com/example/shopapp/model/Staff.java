package com.example.shopapp.model;

import com.example.shopapp.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "staff")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String code;
    @Column(name = "user_name")
    String userName;
    String password;
    Role role;
    Boolean status = false;
    @OneToMany( mappedBy = "staff" ,fetch =  FetchType.LAZY, cascade = CascadeType.ALL)
    List<Order> orders;
}