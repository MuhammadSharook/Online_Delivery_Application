package com.example.demo.service;

import com.example.demo.dto.request.ListItemRequest;
import com.example.demo.dto.request.VendorRequest;
import com.example.demo.dto.response.VendorResponse;

public interface VendorService {

    public  VendorResponse addVendor(VendorRequest vendorRequest);

   public VendorResponse getVendorByMobile(String mobileNo);

    VendorResponse addListItemToVendor(ListItemRequest listItemRequest);
}
