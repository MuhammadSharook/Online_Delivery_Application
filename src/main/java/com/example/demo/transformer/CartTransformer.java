package com.example.demo.transformer;

import com.example.demo.dto.response.CartResponse;
import com.example.demo.dto.response.ProductItemResponse;
import com.example.demo.model.Cart;
import com.example.demo.model.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {

    public static CartResponse CartToCartResponse(Cart cart){

        List<ProductItemResponse> productItemsList = new ArrayList<>();

        for(ProductItem productItem : cart.getProductItems())
        {
            productItemsList.add(ProductItemTransformer.fromProductItemToProductItemResponse(productItem));
        }
        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .productItems(productItemsList)
                .build();
    }
}
