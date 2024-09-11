package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String content;

    @Size(min = 1,max = 5)
    int rating;

    Date date;

    @ManyToOne
    @JoinColumn(name = "customer",nullable = false)
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "productItem",nullable = false)
    ProductItem productItem;


}
