package com.example.demo.service;

import com.example.demo.dto.response.OrderEntityResponse;

public interface OrderService {
    OrderEntityResponse placeOrder(String mobileNo);
}
