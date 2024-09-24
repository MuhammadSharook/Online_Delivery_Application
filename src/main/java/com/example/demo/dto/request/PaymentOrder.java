package com.example.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentOrder {

    double price;
    String currency;
    String method;
    String intent;
    String description;
}
