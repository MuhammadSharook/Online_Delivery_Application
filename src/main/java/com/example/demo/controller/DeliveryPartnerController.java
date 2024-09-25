package com.example.demo.controller;

import com.example.demo.dto.request.DeliveryPartnerRequest;
import com.example.demo.dto.response.DeliveryPartnerResponse;
import com.example.demo.service.DeliveryPartnerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery-partner")
public class DeliveryPartnerController {

    private final DeliveryPartnerService deliveryPartnerService;

    @Autowired
    public DeliveryPartnerController(DeliveryPartnerService deliveryPartnerService) {
        this.deliveryPartnerService = deliveryPartnerService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN','DELIVERY-PARTNER')")
    public ResponseEntity addDeliveryPartner(@RequestBody DeliveryPartnerRequest deliveryPartnerRequest){

        DeliveryPartnerResponse deliveryPartnerResponse = deliveryPartnerService.addDeliveryPartner(deliveryPartnerRequest);
        return new ResponseEntity(deliveryPartnerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find/mobile/{mobile}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity findDeliveryPartnerByMobileNo(@PathVariable("mobile") String mobileNo){
        try{
            DeliveryPartnerResponse deliveryPartnerResponse = deliveryPartnerService.findDeliveryPartnerByMobileNo(mobileNo);
            return new ResponseEntity(deliveryPartnerResponse,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/old-mobileNo/{oldMObileNo}/new-mobileNo{newMobileNo}")
    @PreAuthorize("hasAuthority('ADMIN','DELIVERY-PARTNER')")
    public ResponseEntity updateMobileNo(@PathVariable("oldMobileNo")String oldMobileNo,@PathVariable("newMobileNo") String newMobileNo){
        try{
            String response = deliveryPartnerService.updateMobileNo(oldMobileNo,newMobileNo);
            return new ResponseEntity(response,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/see-orders/mobileNo{mobileNo}")
    @PreAuthorize("hasAuthority('ADMIN','DELIVERY-PARTNER')")
    public ResponseEntity seeAllOrders(@PathVariable("mobileNo")String mobileNo){
        try{
            DeliveryPartnerResponse deliveryPartnerResponse = deliveryPartnerService.seeAllOrders(mobileNo);
            return new ResponseEntity(deliveryPartnerResponse,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{mobileNo}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity deleteDeliveryPartner(@PathVariable("mobileNo")String mobileNo){
        try{
            String response = deliveryPartnerService.deleteDeliveryPartner(mobileNo);
            return new ResponseEntity(response,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
