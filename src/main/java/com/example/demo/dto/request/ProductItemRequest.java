package com.example.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemRequest {

    int requiredQuantity;

    String customerMobile;

    int listItemId;
}
