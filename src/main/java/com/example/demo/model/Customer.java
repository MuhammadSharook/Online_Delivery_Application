package com.example.demo.model;

import com.example.demo.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min = 2,  message = "{validation.name.size.too_short}")
    @Size(max = 16,  message = "{validation.name.size.too_long}")
    String name;

    @Email
    @Column(unique = true)
    String email;

    String address;

    @Column(unique = true)
    @Size(min = 10, max = 10)
    String mobileNo;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy = "custoner",cascade = CascadeType.ALL)
    List<OrderEntity> orders = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Admin admin;
}
