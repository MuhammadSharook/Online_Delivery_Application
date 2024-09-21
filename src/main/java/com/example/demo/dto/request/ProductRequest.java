package com.example.demo.dto.request;

import com.example.demo.Enum.Category;
import com.example.demo.Enum.SubCategory;
import com.example.demo.model.ListItem;
import com.example.demo.model.Vendor;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    int requiredQuantity;

    double price;

    String productName;

    String discription;

    Category category;

    SubCategory subCategory;

}
