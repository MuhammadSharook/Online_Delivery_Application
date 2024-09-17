package com.example.demo.service;

import com.example.demo.dto.request.CustomerRequest;
import com.example.demo.dto.response.CustomerResponse;

public interface CustomerService {

    public CustomerResponse addCustomer(CustomerRequest customerRequest);

    public CustomerResponse findCustomerByMobile(String mobileNo);
}
