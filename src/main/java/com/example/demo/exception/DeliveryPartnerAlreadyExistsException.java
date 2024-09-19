package com.example.demo.exception;

public class DeliveryPartnerAlreadyExistsException extends RuntimeException{
    public DeliveryPartnerAlreadyExistsException(String message){
        super(message);
    }

}
