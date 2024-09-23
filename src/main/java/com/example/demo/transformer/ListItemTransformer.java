package com.example.demo.transformer;

import com.example.demo.dto.request.ListItemRequest;
import com.example.demo.dto.response.ListItemResponse;
import com.example.demo.model.ListItem;

public class ListItemTransformer {

    public static ListItem ListItemRequestToListItem(ListItemRequest listItemRequest){
        return ListItem.builder()
                .prodectName(listItemRequest.getProductRequest().getProductName())
                .price(listItemRequest.getProductRequest().getPrice())
                .category(listItemRequest.getProductRequest().getCategory())
                .subCategory(listItemRequest.getProductRequest().getSubCategory())
                .available(listItemRequest.isAvailable())
                .build();
    }

    public static ListItemResponse listItemToListItemResponse(ListItem listItem){
        return ListItemResponse.builder()
                .productName(listItem.getProdectName())
                .price(listItem.getPrice())
                .category(listItem.getCategory())
                .subCategory(listItem.getSubCategory())
                .build();
    }
}
