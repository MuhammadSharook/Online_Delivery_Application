package com.example.demo.service.Implementation;

import com.example.demo.dto.request.CustomerRequest;
import com.example.demo.dto.response.CustomerResponse;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerServiceImpl implements CustomerService {
    

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }


    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        //dto to customer model
        Customer customer = CustomerTransformer.CustomerRequestToCustomer(customerRequest);

        //allocate cart
        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        customer.setCart(cart);
        customer.setCommentList(new ArrayList<>());
        customer.setGender(customerRequest.getGender());
        customer.setPassword(customerRequest.getPassword());
        customer.setRole(customer.getRole());

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerTransformer.CustomerToCustomerResponse(savedCustomer);
    }


    public CustomerResponse findCustomerByMobile(String mobileNo) {
        Customer customer = customerRepository.findByMobileNo(mobileNo);
        if(customer == null)
        {
            throw new CustomerNotFoundException("Invalid mobile No !!!");
        }
        return CustomerTransformer.CustomerToCustomerResponse(customer);
    }

    @Override
    public String deleteCustomer(String mobileNo) {

        Customer customer = customerRepository.findByMobileNo(mobileNo);

        if(customer == null){
            throw new CustomerNotFoundException("Customer not found!!!");
        }

        Cart cart = customer.getCart();
        cartRepository.delete(cart);

        customerRepository.delete(customer);

        return "Successfully deleted!!!";

    }
}
