package com.example.demo.service.Implementation;

import com.example.demo.dto.request.ListItemRequest;
import com.example.demo.dto.request.VendorRequest;
import com.example.demo.dto.response.VendorResponse;
import com.example.demo.exception.VendorNotFoundException;
import com.example.demo.model.ListItem;
import com.example.demo.model.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import com.example.demo.transformer.ListItemTransformer;
import com.example.demo.transformer.VendorTransformer;
import com.example.demo.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    private final ValidationUtils validationUtils;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository,
                             ValidationUtils validationUtils) {
        this.vendorRepository = vendorRepository;
        this.validationUtils = validationUtils;
    }


    @Override
    public VendorResponse addVendor(VendorRequest vendorRequest) {
        Vendor vendor = VendorTransformer.VendorRequestToVendor(vendorRequest);
        vendor.setAvailableListItems(new ArrayList<>());
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

    @Override
    public VendorResponse addListItemToVendor(ListItemRequest listItemRequest) {
        if(!validationUtils.validateVendor(listItemRequest.getVendorId())){
            throw new VendorNotFoundException("Vendor does not exists!!!");
        }

        Vendor vendor = vendorRepository.findById(listItemRequest.getVendorId()).get();

        ListItem listItem = ListItemTransformer.ListItemRequestToListItem(listItemRequest);
        listItem.setVendor(vendor);

        vendor.getAvailableListItems().add(listItem);

        Vendor savedVendor = vendorRepository.save(vendor);

        return VendorTransformer.VendorToVendorResponse(savedVendor);

    }


}
