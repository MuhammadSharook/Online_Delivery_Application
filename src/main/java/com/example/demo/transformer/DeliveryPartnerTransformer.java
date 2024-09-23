package com.example.demo.transformer;

import com.example.demo.dto.request.DeliveryPartnerRequest;
import com.example.demo.dto.response.DeliveryPartnerResponse;
import com.example.demo.dto.response.OrderEntityResponse;
import com.example.demo.model.DeliveryPartner;
import com.example.demo.model.OrderEntity;

import java.util.ArrayList;
import java.util.List;


public class DeliveryPartnerTransformer {

    public static DeliveryPartner fromDeliveryPartnerRequesttoDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest){
        return DeliveryPartner.builder()
                .name(deliveryPartnerRequest.getName())
                .mobileNo(deliveryPartnerRequest.getMobileNo())
                .gender(deliveryPartnerRequest.getGender())
                .role(deliveryPartnerRequest.getRole())
                .orders(new ArrayList<>())
                .build();
    }

    public static DeliveryPartnerResponse fromDeliverPartnerToDeliveryPartnerResponse(DeliveryPartner deliveryPartner){

        List<OrderEntityResponse> orderEntityResponse = new ArrayList<>();

        for(OrderEntity order : deliveryPartner.getOrders()){
            orderEntityResponse.add(OrderEntityTransformer.fromOrderEntityToOrderEntityResponse(order));
        }
        return DeliveryPartnerResponse.builder()
                .name(deliveryPartner.getName())
                .mobileNo(deliveryPartner.getMobileNo())
                .orders(orderEntityResponse)
                .build();
    }
}
