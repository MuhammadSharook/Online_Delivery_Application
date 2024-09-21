package com.example.demo.transformer;

import com.example.demo.dto.request.ProductRequest;
import com.example.demo.dto.response.ProductItemResponse;
import com.example.demo.dto.response.ProductItemResponsewithVendorName;
import com.example.demo.model.ProductItem;

public class ProductItemTransformer {

    public static ProductItemResponse fromProductItemToProductItemResponse(ProductItem productItem) {
        return ProductItemResponse.builder()
                .productName(productItem.getProductName())
                .discription(productItem.getDiscription())
                .requiredQuantity(productItem.getRequiredQuantity())
                .price(productItem.getPrice())
                .build();
    }

    public static ProductItemResponsewithVendorName fromProductItemToProductItemResponsewithVendorName(ProductItem productItem){
        return ProductItemResponsewithVendorName.builder()
                .productName(productItem.getProductName())
                .price(productItem.getPrice())
                .category(productItem.getListItem().getCategory())
                .available(productItem.getListItem().isAvailable())
                .vendorName(productItem.getListItem().getVendor().getName())
                .build();
    }

    public static ProductItem fromProductRequestToProductItem(ProductRequest productRequest){
        return ProductItem.builder()
                .productName(productRequest.getProductName())
                .discription(productRequest.getDiscription())
                .requiredQuantity(productRequest.getRequiredQuantity())
                .price(productRequest.getPrice())
                .category(productRequest.getCategory())
                .subCategory(productRequest.getSubCategory())
                .build();
    }
}
