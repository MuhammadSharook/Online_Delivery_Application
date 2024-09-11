package com.example.demo.dto.request;

import com.example.demo.Enum.VendorCategory;
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

    VendorCategory vendorCategory;

    String contactNo;


}
