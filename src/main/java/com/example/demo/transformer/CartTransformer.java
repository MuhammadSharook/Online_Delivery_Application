package com.example.demo.transformer;

import com.example.demo.dto.response.CartResponse;
import com.example.demo.model.Cart;

import java.util.ArrayList;

public class CartTransformer {

    public static CartResponse CartToCartResponse(Cart cart){
        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .productItems(new ArrayList<>())
                .build();
    }
}
