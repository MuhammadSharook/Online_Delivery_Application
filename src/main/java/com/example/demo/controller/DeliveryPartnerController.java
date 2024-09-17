package com.example.demo.controller;

import com.example.demo.dto.request.DeliveryPartnerRequest;
import com.example.demo.dto.response.DeliveryPartnerResponse;
import com.example.demo.service.DeliveryPartnerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delivery-partner")
public class DeliveryPartnerController {

    private final DeliveryPartnerService deliveryPartnerService;

    @Autowired
    public DeliveryPartnerController(DeliveryPartnerService deliveryPartnerService) {
        this.deliveryPartnerService = deliveryPartnerService;
    }

    @PostMapping("/add")
    public ResponseEntity addDeliveryPartner(@RequestBody DeliveryPartnerRequest deliveryPartnerRequest){
        DeliveryPartnerResponse deliveryPartnerResponse = deliveryPartnerService.addDeliveryPartner(deliveryPartnerRequest);
        return new ResponseEntity(deliveryPartnerResponse, HttpStatus.CREATED);
    }

}
