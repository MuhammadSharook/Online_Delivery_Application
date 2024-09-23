package com.example.demo.service.Implementation;

import com.example.demo.Enum.OrderStatus;
import com.example.demo.dto.response.OrderEntityResponse;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.EmptyCartException;
import com.example.demo.exception.OrderNotfoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OrderService;
import com.example.demo.transformer.OrderEntityTransformer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;
    private final OrderEntityRepository orderEntityRepository;
    private final DeliveryPartnerRepository deliveryPartnerRepository;
    private final VendorRepository vendorRepository;

    public OrderServiceImpl(CustomerRepository customerRepository,
                            OrderEntityRepository orderEntityRepository,
                            DeliveryPartnerRepository deliveryPartnerRepository,
                            VendorRepository vendorRepository) {
        this.customerRepository = customerRepository;
        this.orderEntityRepository = orderEntityRepository;
        this.deliveryPartnerRepository = deliveryPartnerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public OrderEntityResponse placeOrder(String customerMobileNo) {
        Customer customer = customerRepository.findByMobileNo(customerMobileNo);

        if(customer == null)
        {
            throw new CustomerNotFoundException("Invalid Mobile Number !!!!");
        }

        Cart cart = customer.getCart();
        if(cart.getProductItems().size() == 0){
            throw new EmptyCartException("Sorry! Your cart is empty!!!!!");
        }


        DeliveryPartner deliveryPartner = deliveryPartnerRepository.findRandomDeliveryPartner();
        Vendor vendor = cart.getProductItems().get(0).getListItem().getVendor();


        OrderEntity order = OrderEntityTransformer.prepareOrderEntity(cart);

        OrderEntity savedOrder = orderEntityRepository.save(order);

        order.setCustomer(customer);
        order.setDeliveryPartner(deliveryPartner);
        order.setVendor(vendor);
        order.setProductItems(cart.getProductItems());
        order.setOrderStatus(OrderStatus.ORDER_PLACED);



        customer.getOrders().add(savedOrder);
        deliveryPartner.getOrders().add(savedOrder);
        vendor.getOrders().add(savedOrder);


        for(ProductItem productItem : cart.getProductItems()){
            productItem.setCart(null);
            productItem.setOrder(savedOrder);
        }


        clearCart(cart);

        customerRepository.save(customer);
        deliveryPartnerRepository.save(deliveryPartner);
        vendorRepository.save(vendor);


        return OrderEntityTransformer.fromOrderEntityToOrderEntityResponse(savedOrder);

    }

    @Override
    public List<OrderEntity> getOrderHistory(int customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        if(customer == null)
        {
            throw new CustomerNotFoundException("Invalid Customer Id!!!");
        }

        List<OrderEntity> orderEntityList = orderEntityRepository.findByCustomerId(customerId);
        if(orderEntityList.isEmpty())
        {
            throw new OrderNotfoundException("You have Zero orders!!!");
        }

        return orderEntityList;
    }

    @Override
    public OrderEntityResponse updateOrderStatus(int orderId, OrderStatus status) {
        OrderEntity order = orderEntityRepository.findById(orderId).get();
        if(order == null)
        {
            throw new OrderNotfoundException("Invalid Order Id!!!");
        }

        order.setOrderStatus(status);

        OrderEntity savedOrder = orderEntityRepository.save(order);

        return OrderEntityTransformer.fromOrderEntityToOrderEntityResponse(savedOrder);
    }

    private void clearCart(Cart cart){
        cart.setCartTotal(0);
        cart.setProductItems(new ArrayList<>());
    }
}
