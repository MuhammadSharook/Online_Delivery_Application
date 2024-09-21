package com.example.demo.dto.request;

import com.example.demo.Enum.SubCategory;
import com.example.demo.Enum.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListItemRequest {

    int vendorId;

    ProductRequest productRequest;

    boolean available;


}
