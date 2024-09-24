package com.example.demo.controller;

import com.example.demo.dto.request.PaymentOrder;
import com.example.demo.service.Implementation.PaypalServiceImpl;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaypalController {

    private final PaypalServiceImpl paypalService;

    @Autowired
    public PaypalController(PaypalServiceImpl paypalService) {
        this.paypalService = paypalService;
    }

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @PostMapping("/pay")
    public String payments(@ModelAttribute("paymentorder") PaymentOrder paymentOrder){
        try {
            Payment payment = paypalService.createPayment(paymentOrder.getPrice(),paymentOrder.getCurrency(),paymentOrder.getMethod(),paymentOrder.getIntent(),paymentOrder.getDescription(),"http://locolhost:8080/"+CANCEL_URL,"http://locolhost:8080/"+SUCCESS_URL);
            for(Links link : payment.getLinks()){
                if(link.getRel().equals("approval_url")){
                    return "redirect:"+link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }
}
