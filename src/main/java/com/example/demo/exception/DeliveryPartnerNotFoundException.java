package com.example.demo.exception;

public class DeliveryPartnerNotFoundException extends RuntimeException{

    public DeliveryPartnerNotFoundException(String message)
    {
        super(message);
    }
}
