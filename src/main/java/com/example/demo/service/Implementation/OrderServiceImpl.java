package com.example.demo.service.Implementation;

import com.example.demo.Enum.Coupon;
import com.example.demo.dto.response.OrderEntityResponse;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.EmptyCartException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OrderService;
import com.example.demo.transformer.OrderEntityTransformer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;
    private final OrderEntityRepository orderEntityRepository;
    private final DeliveryPartnerRepository deliveryPartnerRepository;
    private final VendorRepository vendorRepository;

    private final BillRepository billRepository;

    public OrderServiceImpl(CustomerRepository customerRepository,
                            OrderEntityRepository orderEntityRepository,
                            DeliveryPartnerRepository deliveryPartnerRepository,
                            VendorRepository vendorRepository,
                            BillRepository billRepository) {
        this.customerRepository = customerRepository;
        this.orderEntityRepository = orderEntityRepository;
        this.deliveryPartnerRepository = deliveryPartnerRepository;
        this.vendorRepository = vendorRepository;
        this.billRepository = billRepository;
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


        customer.setNoOfOrders(customer.getNoOfOrders() + 1);
        DeliveryPartner deliveryPartner = deliveryPartnerRepository.findRandomDeliveryPartner();
        Vendor vendor = cart.getProductItems().get(0).getListItem().getVendor();


        Bill bill1 = new Bill();
        Bill bill = billRepository.save(bill1);

        OrderEntity order = OrderEntityTransformer.prepareOrderEntity(cart);

        OrderEntity savedOrder = orderEntityRepository.save(order);

        order.setCustomer(customer);
        order.setDeliveryPartner(deliveryPartner);
        order.setVendor(vendor);
        order.setProductItems(cart.getProductItems());


        customer.getOrders().add(savedOrder);
        deliveryPartner.getOrders().add(savedOrder);
        vendor.getOrders().add(savedOrder);
        bill.setOrder(savedOrder);
        savedOrder.setBill(bill);


        for(ProductItem productItem : cart.getProductItems()){
            productItem.setCart(null);
            productItem.setOrder(savedOrder);
        }

        double cartValue = cart.getCartTotal();
        cart.setCartTotal(0);

        clearCart(cart);

        customerRepository.save(customer);
        deliveryPartnerRepository.save(deliveryPartner);
        vendorRepository.save(vendor);

        bill.setOrdervalue(cartValue);
        bill.setGst((double) (0.05 * cartValue));

        Coupon coupon = null;
        if(customer.getNoOfOrders() == 1)
        {
            coupon = Coupon.FIRST_ORDER;
        } else if (bill.getOrdervalue() > 499 && bill.getOrdervalue() <= 799) {
            coupon = Coupon.ORDER_ABOVE_499;
        } else if (bill.getOrdervalue() > 799 && bill.getOrdervalue() <= 1999) {
            coupon = Coupon.ORDER_ABOVE_799;
        } else if (bill.getOrdervalue() > 1999) {
            coupon = Coupon.ORDER_ABOVE_1999;
        }
        else {
            coupon = Coupon.N0_COUPON_APPLIED;
        }

        bill.setCoupon(coupon);

        double discount = 0;
        double gstPlusOrderVal = (0.05 * cartValue) + cartValue;

        if(bill.getCoupon().equals(Coupon.FIRST_ORDER)){
            discount = ((double) 30 / 100) * gstPlusOrderVal;
        } else if (bill.getCoupon().equals(Coupon.ORDER_ABOVE_499)) {
            discount = ((double) 5 / 100) * gstPlusOrderVal;
        } else if (bill.getCoupon().equals(Coupon.ORDER_ABOVE_799)) {
            discount = ((double) 7 / 100) * gstPlusOrderVal;
        } else if (bill.getCoupon().equals(Coupon.ORDER_ABOVE_1999)) {
            discount = ((double) 10 / 100) * gstPlusOrderVal;
        }

        bill.setDiscount(discount);
        bill.setBillAmount(gstPlusOrderVal - discount);

        return OrderEntityTransformer.fromOrderEntityToOrderEntityResponse(savedOrder);

    }

    private void clearCart(Cart cart){
        cart.setCartTotal(0);
        cart.setProductItems(new ArrayList<>());
    }
}
