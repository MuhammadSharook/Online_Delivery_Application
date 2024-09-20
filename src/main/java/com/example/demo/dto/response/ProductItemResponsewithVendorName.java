package com.example.demo.dto.response;

import com.example.demo.Enum.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemResponsewithVendorName {

    String productName;

    double price;

    boolean available;

    Category category;

    String vendorName;
}
