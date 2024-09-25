package com.example.demo.dto.request;

import com.example.demo.Enum.Role;
import com.example.demo.Enum.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorRequest {

    String name;

    String location;

    Category category;

    String contactNo;

//    SubCategory subCategory;

    String role;
}
