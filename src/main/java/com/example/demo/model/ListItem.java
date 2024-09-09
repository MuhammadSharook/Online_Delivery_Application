package com.example.demo.model;

import com.example.demo.Enum.VendorCategory;
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
@Table(name = "category_list_item")
public class ListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String prodectName;

    double price;

    @Enumerated(EnumType.STRING)
    VendorCategory category;

    boolean available;

    @ManyToOne
    @JoinColumn
    Vendor vendor;

    @OneToMany(mappedBy = "listItem",cascade = CascadeType.ALL)
    List<ProductItem> productItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Admin admin;

    @ManyToOne
    @JoinColumn
    Cart cart;

}
