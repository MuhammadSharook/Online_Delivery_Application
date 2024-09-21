package com.example.demo.service;

import com.example.demo.Enum.OrderStatus;
import com.example.demo.dto.response.OrderEntityResponse;
import com.example.demo.model.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderEntityResponse placeOrder(String mobileNo);

    List<OrderEntity> getOrderHistory(int customerId);

    OrderEntityResponse updateOrderStatus(int orderId, OrderStatus status);
}
