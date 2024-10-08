package com.example.demo.model;


import com.example.demo.Enum.Role;
import com.example.demo.Enum.SubCategory;
import com.example.demo.Enum.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min = 2,  message = "{validation.name.size.too_short}")
    @Size(max = 16,  message = "{validation.name.size.too_long}")
    String name;


    String location;

    @Enumerated(EnumType.STRING)
    Category category;

    @Enumerated(EnumType.STRING)
    SubCategory subCategory;

    @Column(unique = true,nullable = false)
    @Size(min = 10,max = 10)
    String mobileNo;

    String role;

    @OneToMany(mappedBy = "vendor",cascade = CascadeType.ALL)
    List<OrderEntity> orders = new ArrayList<>();

    @OneToMany(mappedBy = "vendor",cascade = CascadeType.ALL)
    List<ListItem> availableListItems = new ArrayList<>();

}
