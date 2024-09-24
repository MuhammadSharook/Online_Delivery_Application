package com.example.demo.model;

import com.example.demo.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="order_entity")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String orderId;

    double orderTotal;

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @CreationTimestamp
    Date orderTime;

    String razorpayOrderId;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @ManyToOne
    @JoinColumn
    DeliveryPartner deliveryPartner;

    @ManyToOne
    @JoinColumn
    Vendor vendor;


    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<ProductItem> productItems = new ArrayList<>();

//    @ManyToOne
//    Admin admin;
}
