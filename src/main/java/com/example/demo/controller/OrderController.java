package com.example.demo.controller;

import com.example.demo.Enum.OrderStatus;
import com.example.demo.dto.response.OrderEntityResponse;
import com.example.demo.model.OrderEntity;
import com.example.demo.service.OrderService;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place-order/{mobile}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity placeOrder(@PathVariable("mobile") String mobileNo){
        try{
            OrderEntityResponse orderEntityResponse = orderService.placeOrder(mobileNo);
            return new ResponseEntity(orderEntityResponse, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/history/{customerId}")
    @PreAuthorize("hasAuthority('ADMIN','CUSTOMER')")
    public ResponseEntity getOrderHistory(@PathVariable("customerId")int customerId){
       try {
           List<OrderEntity> orders = orderService.getOrderHistory(customerId);
           return new ResponseEntity(orders, HttpStatus.FOUND);
       }catch (Exception e){
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping("/status{orderId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity updateOrderStatus(@PathVariable("orderId")int orderId, @RequestBody OrderStatus status){
        try {
            OrderEntityResponse order = orderService.updateOrderStatus(orderId, status);
            return new ResponseEntity(order, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/orders")
    public String ordersPage(){
        return "orders";
    }
}
