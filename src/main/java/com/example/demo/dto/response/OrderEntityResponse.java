package com.example.demo.dto.response;

import com.example.demo.Enum.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntityResponse {

    String orderId;

    double orderTotoal;

    Date orderTime;

    OrderStatus status;

    String customerName;

    String customerMobileNo;

    String deliveryPartnerName;

    String deliveryPartnerMobileNo;

    String vendorName;

    List<ProductItemResponse> productItemResponseList;
}
