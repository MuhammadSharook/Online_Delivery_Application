package com.example.demo.service;

import com.example.demo.dto.request.DeliveryPartnerRequest;
import com.example.demo.dto.response.DeliveryPartnerResponse;

public interface DeliveryPartnerService {
    DeliveryPartnerResponse addDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest);
}
