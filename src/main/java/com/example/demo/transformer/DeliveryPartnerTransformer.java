package com.example.demo.transformer;

import com.example.demo.dto.request.DeliveryPartnerRequest;
import com.example.demo.dto.response.DeliveryPartnerResponse;
import com.example.demo.dto.response.OrderEntityResponse;
import com.example.demo.model.DeliveryPartner;
import com.example.demo.model.OrderEntity;

import java.util.ArrayList;
import java.util.List;


public class DeliveryPartnerTransformer {

    public DeliveryPartner fromDeliveryPartnerRequesttoDeliveryPArtner(DeliveryPartnerRequest deliveryPartnerRequest){
        return DeliveryPartner.builder()
                .name(deliveryPartnerRequest.getName())
                .mobileNO(deliveryPartnerRequest.getMobileNo())
                .gender(deliveryPartnerRequest.getGender())
                .role(deliveryPartnerRequest.getRole())
                .orders(new ArrayList<>())
                .build();
    }

    public DeliveryPartnerResponse fromDeliverPartnerToDeliveryPartnerResponse(DeliveryPartner deliveryPartner){

        List<OrderEntityResponse> orderEntityResponse = new ArrayList<>();

        for(OrderEntity order : deliveryPartner.getOrders()){
            orderEntityResponse.add(OrderEntityTransformer.fromOrderEntityToOrderEntityResponse(order));
        }
        return DeliveryPartnerResponse.builder()
                .name(deliveryPartner.getName())
                .mobileNo(deliveryPartner.getMobileNO())
                .orders(orderEntityResponse)
                .build();
    }
}
