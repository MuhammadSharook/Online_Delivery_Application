package com.example.demo.controller;

import com.example.demo.dto.request.ProductItemRequest;
import com.example.demo.dto.response.CartResponse;
import com.example.demo.dto.response.CartStatusResponse;
import com.example.demo.dto.response.CustomerResponse;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-product-to-cart/")
    @PreAuthorize("hasAuthority('ADMIN','CUSTOMER')")
    public ResponseEntity addProductToCart(@RequestBody ProductItemRequest productItemRequest){
        try{
            CartStatusResponse cartStatusResponse = cartService.addProductToCart(productItemRequest);
            return new ResponseEntity(cartStatusResponse, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
