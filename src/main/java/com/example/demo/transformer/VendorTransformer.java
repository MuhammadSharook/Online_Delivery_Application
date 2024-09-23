package com.example.demo.transformer;

import com.example.demo.dto.request.VendorRequest;
import com.example.demo.dto.response.ListItemResponse;
import com.example.demo.dto.response.ProductItemResponse;
import com.example.demo.dto.response.VendorResponse;
import com.example.demo.model.ListItem;
import com.example.demo.model.ProductItem;
import com.example.demo.model.Vendor;

import java.util.ArrayList;
import java.util.List;

public class VendorTransformer {

    public static Vendor VendorRequestToVendor(VendorRequest vendorRequest)
    {
        return Vendor.builder()
                .name(vendorRequest.getName())
                .location(vendorRequest.getLocation())
                .mobileNo(vendorRequest.getContactNo())
                .category(vendorRequest.getCategory())
                .build();
    }

    public static VendorResponse VendorToVendorResponse(Vendor vendor)
    {
        List<ListItemResponse> listItemResponseArrayList = new ArrayList<>();
        for(ListItem listItem : vendor.getAvailableListItems())
        {
            listItemResponseArrayList.add(ListItemTransformer.listItemToListItemResponse(listItem));
        }
        return VendorResponse.builder()
                .name(vendor.getName())
                .location(vendor.getLocation())
                .listItemResponseList(listItemResponseArrayList)
                .build();
    }
}
