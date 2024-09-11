package com.example.demo.dto.response;

import com.example.demo.Enum.SubCategory;
import com.example.demo.Enum.VendorCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListItemResponse {

    String productName;

    double price;

    SubCategory subCategory;

    VendorCategory vendorCategory;

}
