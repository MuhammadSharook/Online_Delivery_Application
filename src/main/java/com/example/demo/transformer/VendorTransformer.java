package com.example.demo.transformer;

import com.example.demo.dto.request.VendorRequest;
import com.example.demo.dto.response.ProductItemResponse;
import com.example.demo.dto.response.VendorResponse;
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
                .ContactNumber(vendorRequest.getContactNo())
                .category(vendorRequest.getCategory())
                .build();
    }

    public static VendorResponse VendorToVendorResponse(Vendor vendor)
    {
        List<ProductItemResponse> productItemResponseList = new ArrayList<>();
        for(ProductItem productItem : vendor.getProductItemList())
        {
            productItemResponseList.add(ProductItemTransformer.fromProductItemToProductItemResponse(productItem));
        }
        return VendorResponse.builder()
                .name(vendor.getName())
                .location(vendor.getLocation())
                .productItemResponseList(productItemResponseList)
                .build();
    }
}
