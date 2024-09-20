package com.example.demo.service;

import com.example.demo.dto.request.ProductItemRequest;
import com.example.demo.dto.response.CartStatusResponse;

public interface CartService {
    CartStatusResponse addProductToCart(ProductItemRequest productItemRequest);
}
