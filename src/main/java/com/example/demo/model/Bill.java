package com.example.demo.model;

import com.example.demo.Enum.Coupon;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    int id;

    double ordervalue;

    @Enumerated(EnumType.STRING)
    Coupon coupon;

    double discount;

    double billAmount;

    double gst;

    @OneToOne
    @JoinColumn(name = "order")
    OrderEntity order;
}
