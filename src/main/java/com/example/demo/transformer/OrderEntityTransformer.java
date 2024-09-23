package com.example.demo.transformer;

import com.example.demo.dto.response.OrderEntityResponse;
import com.example.demo.dto.response.ProductItemResponse;
import com.example.demo.model.Cart;
import com.example.demo.model.OrderEntity;
import com.example.demo.model.ProductItem;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderEntityTransformer {
public static OrderEntity prepareOrderEntity(Cart cart){
    return OrderEntity.builder()
            .orderId(String.valueOf(UUID.randomUUID()))
            .orderTotal(cart.getCartTotal())
            .build();
}
    public static OrderEntityResponse fromOrderEntityToOrderEntityResponse(OrderEntity savedOrder){

        List<ProductItemResponse> productItemResponseList = new ArrayList<>();
        for(ProductItem productItem : savedOrder.getProductItems()){
            ProductItemResponse productItemResponse = ProductItemResponse.builder()
                    .productName(productItem.getProductName())
                    .price(productItem.getPrice())
                    .requiredQuantity(productItem.getRequiredQuantity())
                    .discription(productItem.getDiscription())
                    .build();

            productItemResponseList.add(productItemResponse);
        }

        return OrderEntityResponse.builder()
                .orderId(savedOrder.getOrderId())
                .orderTime((Date) savedOrder.getOrderTime())
                .orderTotoal(savedOrder.getOrderTotal())
                .customerName(savedOrder.getCustomer().getName())
                .customerMobileNo(savedOrder.getCustomer().getMobileNo())
                .deliveryPartnerName(savedOrder.getDeliveryPartner().getName())
                .deliveryPartnerMobileNo(savedOrder.getDeliveryPartner().getMobileNo())
                .vendorName(savedOrder.getVendor().getName())
                .productItemResponseList(productItemResponseList)
                .build();

    }
}
