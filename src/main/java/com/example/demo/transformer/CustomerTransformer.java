package com.example.demo.transformer;

import com.example.demo.dto.request.CustomerRequest;
import com.example.demo.dto.response.CartResponse;
import com.example.demo.dto.response.CustomerResponse;
import com.example.demo.model.Comment;
import com.example.demo.model.Customer;

import java.util.ArrayList;

public class CustomerTransformer {

    public static Customer CustomerRequestToCustomer(CustomerRequest customerRequest)
    {
        return Customer.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .address(customerRequest.getAddress())
                .mobileNo(customerRequest.getMobileNo())
                .gender(customerRequest.getGender())
                .role(customerRequest.getRole())
                .password(customerRequest.getPassword())
                .build();
    }

    public static CustomerResponse CustomerToCustomerResponse(Customer customer)
    {
        CartResponse cartResponse = CartTransformer.CartToCartResponse(customer.getCart());


        return CustomerResponse.builder()
                .name(customer.getName())
                .mobileNo(customer.getMobileNo())
                .address(customer.getAddress())
                .cart(cartResponse)
                .role(customer.getRole())
                .password(customer.getPassword())
                .gender(customer.getGender())
                .build();
    }
}
