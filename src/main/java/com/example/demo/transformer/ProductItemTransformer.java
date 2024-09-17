package com.example.demo.transformer;

import com.example.demo.dto.response.ProductItemResponse;
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
}
