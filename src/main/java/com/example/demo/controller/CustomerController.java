package com.example.demo.controller;

import com.example.demo.dto.request.CustomerRequest;
import com.example.demo.dto.response.CustomerResponse;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN','CUSTOMER')")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest)
    {
        CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
        return new ResponseEntity(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find/mobile/{mobile}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity getCustomerByMobile(@PathVariable("mobile") String mobileNo){
        try{
            CustomerResponse customerResponse = customerService.findCustomerByMobile(mobileNo);
            return new ResponseEntity(customerResponse,HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{mobileNo}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity deleteCustomer(@PathVariable("mobileNo") String mobileNo){
        try{
            String response = customerService.deleteCustomer(mobileNo);
            return new ResponseEntity(response,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
