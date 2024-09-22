package com.example.demo.service.Implementation;

import com.example.demo.dto.request.DeliveryPartnerRequest;
import com.example.demo.dto.response.DeliveryPartnerResponse;
import com.example.demo.exception.DeliveryPartnerAlreadyExistsException;
import com.example.demo.exception.DeliveryPartnerNotFoundException;
import com.example.demo.exception.VendorNotFoundException;
import com.example.demo.model.DeliveryPartner;
import com.example.demo.repository.DeliveryPartnerRepository;
import com.example.demo.transformer.DeliveryPartnerTransformer;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DeliveryPartnerServiceImpl implements com.example.demo.service.DeliveryPartnerService {

    private final DeliveryPartnerRepository deliveryPartnerRepository;

    @Autowired
    public DeliveryPartnerServiceImpl(DeliveryPartnerRepository deliveryPartnerRepository) {
        this.deliveryPartnerRepository = deliveryPartnerRepository;
    }

    @Override
    public DeliveryPartnerResponse addDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest) {

        DeliveryPartner deliveryPartner1 = deliveryPartnerRepository.findByMobileNo(deliveryPartnerRequest.getMobileNo());


        if(deliveryPartner1 == null){
            throw new DeliveryPartnerAlreadyExistsException("Delivery Partner with this contact already exists.");
        }
        DeliveryPartner deliveryPartner = DeliveryPartnerTransformer.fromDeliveryPartnerRequesttoDeliveryPartner(deliveryPartnerRequest);
//        deliveryPartner.setOrders(new ArrayList<>());

        DeliveryPartner savedDeliveryPartner = deliveryPartnerRepository.save(deliveryPartner);

        return DeliveryPartnerTransformer.fromDeliverPartnerToDeliveryPartnerResponse(savedDeliveryPartner);
    }

    @Override
    public DeliveryPartnerResponse findDeliveryPartnerByMobileNo(String mobileNo) {
        DeliveryPartner deliveryPartner = deliveryPartnerRepository.findByMobileNo(mobileNo);
        if(deliveryPartner == null){
            throw new DeliveryPartnerNotFoundException("Delivery Partner does not exists.");
        }


        return DeliveryPartnerTransformer.fromDeliverPartnerToDeliveryPartnerResponse(deliveryPartner);
    }

    @Override
    public String updateMobileNo(String oldMobileNo, String newMobileNo) {

        if(oldMobileNo.equals(newMobileNo)){
            return "Old and New Mobile No can't be same.";
        }
        DeliveryPartner deliveryPartner = deliveryPartnerRepository.findByMobileNo(oldMobileNo);
        if(deliveryPartner == null){
            throw new DeliveryPartnerNotFoundException("Delivery Partner does not exists.");
        }
        deliveryPartner.setMobileNO(newMobileNo);
        deliveryPartnerRepository.save(deliveryPartner);
        return "Your Mobile No has been changed from [ " + oldMobileNo +"] to [" + newMobileNo + "]";
    }

    @Override
    public DeliveryPartnerResponse seeAllOrders(String mobileNo) {
        DeliveryPartner deliveryPartner = deliveryPartnerRepository.findByMobileNo(mobileNo);
        if(deliveryPartner == null){
            throw new DeliveryPartnerNotFoundException("Delivery Partner does not exists.");
        }

        return DeliveryPartnerTransformer.fromDeliverPartnerToDeliveryPartnerResponse(deliveryPartner);
    }

    @Override
    public String deleteDeliveryPartner(String mobileNo) {
        DeliveryPartner deliveryPartner = deliveryPartnerRepository.findByMobileNo(mobileNo);

        if(deliveryPartner == null){
            throw new DeliveryPartnerNotFoundException("DeliveryPartner not found!!!");
        }

        deliveryPartnerRepository.delete(deliveryPartner);
        return "Successfully deleted DeleveryPartner.";
    }


}
