package com.example.demo.service.Implementation;

import com.example.demo.dto.request.VendorRequest;
import com.example.demo.dto.response.VendorResponse;
import com.example.demo.exception.VendorNotFoundException;
import com.example.demo.model.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import com.example.demo.transformer.VendorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }


    @Override
    public VendorResponse addVendor(VendorRequest vendorRequest) {
        Vendor vendor = VendorTransformer.VendorRequestToVendor(vendorRequest);
        vendor.setProductItemList(new ArrayList<>());
        vendor.setOrders(new ArrayList<>());

        Vendor savedVendor = vendorRepository.save(vendor);
        return VendorTransformer.VendorToVendorResponse(savedVendor);
    }

    @Override
    public VendorResponse getVendorByMobile(String mobileNo) {
        Vendor vendor = vendorRepository.getVendorByMobile(mobileNo);
        if(vendor == null)
        {
            throw new VendorNotFoundException("Invalid Mobile No !!!");
        }
        return VendorTransformer.VendorToVendorResponse(vendor);
    }


}
