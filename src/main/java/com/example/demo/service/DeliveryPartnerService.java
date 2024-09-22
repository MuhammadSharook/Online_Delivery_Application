package com.example.demo.service;

import com.example.demo.dto.request.DeliveryPartnerRequest;
import com.example.demo.dto.response.DeliveryPartnerResponse;

public interface DeliveryPartnerService {
    DeliveryPartnerResponse addDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest);

    DeliveryPartnerResponse findDeliveryPartnerByMobileNo(String mobileNo);

    String updateMobileNo(String oldMobileNo, String newMobileNo);

    DeliveryPartnerResponse seeAllOrders(String mobileNo);

    String deleteDeliveryPartner(String mobileNo);
}
