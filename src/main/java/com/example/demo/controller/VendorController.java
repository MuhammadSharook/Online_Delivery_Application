package com.example.demo.controller;

import com.example.demo.dto.request.ListItemRequest;
import com.example.demo.dto.request.VendorRequest;
import com.example.demo.dto.response.VendorResponse;
import com.example.demo.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    private final VendorService vendorService;


    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN','VENDOR')")
    public ResponseEntity addVendor (@RequestBody VendorRequest vendorRequest){
        VendorResponse vendorResponse = vendorService.addVendor(vendorRequest);
        return new ResponseEntity(vendorResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find/mobile{mobile}")
    @PreAuthorize("hasAuthority('ADMIN','VENDOR')")
    public ResponseEntity getVendorByMobile(@PathVariable("mobile") String mobileNo){
        try{
            VendorResponse vendorResponse = vendorService.getVendorByMobileNo(mobileNo);
            return new ResponseEntity(vendorResponse, HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add/list")
    @PreAuthorize("hasAuthority('ADMIN','VENDOR')")
    public ResponseEntity addListItemToVendor(@RequestBody ListItemRequest listItemRequest){
        VendorResponse vendorResponse = vendorService.addListItemToVendor(listItemRequest);
        return new ResponseEntity(vendorResponse,HttpStatus.CREATED);
    }
}
