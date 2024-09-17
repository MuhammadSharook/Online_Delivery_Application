package com.example.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemResponse {

    int requiredQuantity;

    double price;

    String productName;

    String discription;



}
