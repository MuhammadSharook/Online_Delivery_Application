package com.example.demo.utils;

import com.example.demo.model.*;
import org.springframework.mail.SimpleMailMessage;

import javax.validation.constraints.Email;

public class MailComposer {

    public static SimpleMailMessage composeCustomerRegistrationMail(Customer customer){
        String text = "Dear " + customer.getName()+",\n"
                +"\n"
                +"You have been successfully added to India's most enthusiastic community of online delivery store. Following are the your customer details: \n"
                +"\n"
                +"Your Good Name: "+customer.getName()+"\n"
                +"Email: "+customer.getEmail()+"\n"
                +"Password: "+customer.getPassword()+"\n"
                +"Address: "+customer.getAddress()+"\n"
                +"\n"
                +"We strongly recommend you to keep your password safe. Otherwise someone else will get from your cart on your pocket :):";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sk.sharook106@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Online Delivery Application Heartily Welcomes You!");
        message.setText(text);


        return message;
    }

    public static SimpleMailMessage composeAdminRegistrationMail(Admin admin){
        String text = "Dear " + admin.getName()+",\n"
                +"\n"
                +"You have been successfully added to India's most enthusiastic community of online delivery store. Following are the your admin details: \n"
                +"\n"
                +"Your Good Name: "+admin.getName()+"\n"
                +"Email: "+admin.getMail()+"\n"
                +"Password: "+admin.getPassword()+"\n"
                +"Address: "+admin.getAddress()+"\n"
                +"\n"
                +"We strongly recommend you to keep your password safe.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sk.sharook106@gmail.com");
        message.setTo(admin.getMail());
        message.setSubject("Online Delivery Application Heartily Welcomes You!");
        message.setText(text);


        return message;
    }

    public static SimpleMailMessage composeDeliveryPartnerOnboardingMail(DeliveryPartner deliveryPartner){
        String text = "Dear "+ deliveryPartner.getName()+",\n"
                +"\n"
                +"Online-Delivery-Application welcomes you to our fleet of product delivery partners. Following are your details with Online-Delivery-Application: \n"
                +"\n"
                +"Your Good Name: "+deliveryPartner.getName()+"\n"
                +"Contact: "+deliveryPartner.getMobileNo()+"\n"
                +"Email: "+deliveryPartner.getEmail()+"\n"
                +"Gender: "+deliveryPartner.getGender()+"\n"
                +"\n"
                +"We follow a strict no-discrimination policy against race, caste, religion and gender, and we assure you of having "
                +"a supportive and fair environment to work with Online-Delivery-Application. Before you hop onto delivering your first order, we recommend you to go "
                +"through our policies and guidance on our website to help you adopt best practices as a delivery partner.\n"
                +"\n"
                +"\n"
                +"Keep delivering happiness with  Online-Delivery-Application :)";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sk.sharook106@gmail.com");
        message.setTo(deliveryPartner.getEmail());
        message.setSubject("Welcome to The Online-Delivery-Application Family!");
        message.setText(text);

        return message;
    }

    public static SimpleMailMessage composeOrderPlacementMail(OrderEntity order) {
        String text = "Dear "+order.getCustomer().getName()+",\n"
                +"\n"
                +"You order was placed successfully. It will be processing soon!!!"
                +"Following are your order details: \n"
                +"\n"
                +"Your Good Name: "+order.getCustomer().getName()+"\n"
                +"Address: "+order.getCustomer().getAddress()+"\n"
                +"Order Id: "+order.getOrderId()+"\n"
                +"Vendor: "+order.getVendor().getName()+", "+order.getVendor().getLocation()+"\n"
                +"Amount Payable: "+order.getOrderTotal()+"\n"
                +"\n"
                +"For more details on your order, go through the invoice attached.\n"
                +"\n"
                +"\n"
                +"Happy enjoying :)";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sk.sharook106@gmail.com");
        message.setTo(order.getCustomer().getEmail());
        message.setSubject("Your order is on its way!");
        message.setText(text);

        return message;
    }
}
