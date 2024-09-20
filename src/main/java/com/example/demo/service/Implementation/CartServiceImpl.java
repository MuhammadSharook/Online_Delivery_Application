package com.example.demo.service.Implementation;

import com.example.demo.dto.request.ProductItemRequest;
import com.example.demo.dto.response.CartStatusResponse;
import com.example.demo.dto.response.ProductItemResponse;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.ListItemNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ListItemRepository;
import com.example.demo.repository.ProducttItemRepository;
import com.example.demo.service.CartService;
import com.example.demo.transformer.ProductItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ListItemRepository listItemRepository;
    private final CustomerRepository customerRepository;

    private final ProducttItemRepository producttItemRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository,
                           ListItemRepository listItemRepository,
                           CustomerRepository customerRepository,
                           ProducttItemRepository producttItemRepository) {
        this.cartRepository = cartRepository;
        this.listItemRepository = listItemRepository;
        this.customerRepository = customerRepository;
        this.producttItemRepository = producttItemRepository;
    }

    @Override
    public CartStatusResponse addProductToCart(ProductItemRequest productItemRequest) {

        Customer customer = customerRepository.findByMobileNo(productItemRequest.getCustomerMobile());
        if(customer == null){
            throw new CustomerNotFoundException("Customer does not exists.");
        }
        Optional<ListItem> listItemOptionl = listItemRepository.findById(productItemRequest.getListItemId());
        if(listItemOptionl.isEmpty()){
            throw new ListItemNotFoundException("Item not available at the vendor!!!");
        }

        ListItem listItem = listItemOptionl.get();
        if(!listItem.isAvailable()){
            throw new ListItemNotFoundException("Selected Product is out of stock for now !!!");
        }

        Cart cart = customer.getCart();


        if(cart.getProductItems().size()!=0){
            Vendor currVendor = cart.getProductItems().get(0).getListItem().getVendor();
            Vendor newVendor = listItem.getVendor();

            if(!currVendor.equals(newVendor)){
                List<ProductItem> productItems = cart.getProductItems();
                for(ProductItem productItem : productItems){
                    productItem.setCart(null);
                    productItem.setOrder(null);
                    productItem.setListItem(null);
                }
                cart.setCartTotal(0);
                cart.getProductItems().clear();
                producttItemRepository.deleteAll(productItems);
            }
        }

        boolean alreadyExists = false;
        ProductItem savedProductItem = null;
        if(cart.getProductItems().size()!= 0){
            for(ProductItem productItem : cart.getProductItems()){
                if(productItem.getListItem().getId() == listItem.getId()){
                    savedProductItem = productItem;
                    int curr = productItem.getRequiredQuantity();
                    productItem.setRequiredQuantity(curr+productItemRequest.getRequiredQuantity());
                    alreadyExists = true;
                    break;
                }
            }
        }

        if(!alreadyExists){
            ProductItem productItem = ProductItem.builder()
                    .listItem(listItem)
                    .requiredQuantity(productItemRequest.getRequiredQuantity())
                    .price(productItemRequest.getRequiredQuantity()*listItem.getPrice())
                    .build();

            savedProductItem = producttItemRepository.save(productItem);
            cart.getProductItems().add(savedProductItem);
            listItem.getProductItems().add(savedProductItem);
            savedProductItem.setCart(cart);

        }

        double carrTotal = 0;
        for(ProductItem product : cart.getProductItems()){
            carrTotal += product.getRequiredQuantity()*product.getListItem().getPrice();
        }

        cart.setCartTotal(carrTotal);


        Cart savedCart = cartRepository.save(cart);
        ListItem savedListItem = listItemRepository.save(listItem);


        List<ProductItemResponse> productItemResponseList = new ArrayList<>();
        for(ProductItem product : cart.getProductItems()){
            productItemResponseList.add(ProductItemTransformer.fromProductItemToProductItemResponse(product));
        }

        return CartStatusResponse.builder()
                .customerName(savedCart.getCustomer().getName())
                .customerMobileNo(savedCart.getCustomer().getMobileNo())
                .customerAddress(savedCart.getCustomer().getAddress())
                .vendorName(savedListItem.getVendor().getName())
                .productItemResponseList(productItemResponseList)
                .cartTotal(carrTotal)
                .build();
    }
}
