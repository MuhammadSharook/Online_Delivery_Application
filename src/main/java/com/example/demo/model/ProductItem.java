package com.example.demo.model;

import com.example.demo.Enum.Category;
import com.example.demo.Enum.SubCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_item")
public class ProductItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int requiredQuantity;

    double price;

    String productName;

    String discription;

    @Enumerated(EnumType.STRING)
    Category category;

    @Enumerated(EnumType.STRING)
    SubCategory subCategory;

    @ManyToOne
    @JoinColumn
    ListItem listItem;

    @ManyToOne
    @JoinColumn
    OrderEntity order;

    @ManyToOne
    Admin admin;

    @ManyToOne
    @JoinColumn
    Cart cart;


    @OneToMany(mappedBy = "productItem",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Comment> commentList = new ArrayList<>();
}
