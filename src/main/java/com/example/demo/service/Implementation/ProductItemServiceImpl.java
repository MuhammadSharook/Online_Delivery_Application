package com.example.demo.service.Implementation;

import com.example.demo.Enum.Category;
import com.example.demo.dto.request.ListItemRequest;
import com.example.demo.dto.request.ProductRequest;
import com.example.demo.dto.response.ProductItemResponse;
import com.example.demo.dto.response.ProductItemResponsewithVendorName;
import com.example.demo.exception.ProductItemNotFoundException;
import com.example.demo.exception.VendorNotFoundException;
import com.example.demo.model.Comment;
import com.example.demo.model.ListItem;
import com.example.demo.model.ProductItem;
import com.example.demo.model.Vendor;
import com.example.demo.repository.ListItemRepository;
import com.example.demo.repository.ProducttItemRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.ProductItemService;
import com.example.demo.transformer.ProductItemTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductItemServiceImpl implements ProductItemService {

    private final ProducttItemRepository producttItemRepository;
    private final VendorRepository vendorRepository;
    private final ListItemRepository listItemRepository;

    public ProductItemServiceImpl(ProducttItemRepository producttItemRepository,
                                  VendorRepository vendorRepository,
                                  ListItemRepository listItemRepository) {
        this.producttItemRepository = producttItemRepository;
        this.vendorRepository = vendorRepository;
        this.listItemRepository = listItemRepository;
    }

    @Override
    public List<ProductItemResponsewithVendorName> getProductFromCategory(Category category) {
        List<ProductItem> productItemList = producttItemRepository.findByCategory(category);
        if(productItemList.isEmpty()){
            throw new ProductItemNotFoundException("No " + category + "product found.");
        }
        List<ProductItemResponsewithVendorName> response = new ArrayList<>();
        for(ProductItem productItem : productItemList){
            ProductItemResponsewithVendorName responsewithVendorName = ProductItemTransformer.fromProductItemToProductItemResponsewithVendorName(productItem);
            response.add(responsewithVendorName);
        }
        return response;
    }

    @Override
    public void addCommentToProduct(int productId, Comment comment) {
        Optional<ProductItem> productItemOptional = producttItemRepository.findById(productId);
        if(productItemOptional.isEmpty()){
            throw new ProductItemNotFoundException("Product not found!!!");
        }
        ProductItem productItem = productItemOptional.get();
        productItem.getCommentList().add(comment);
        producttItemRepository.save(productItem);
    }

    @Override
    public ProductItemResponse addProductItem(ListItemRequest listItemRequest) {
        Vendor vendor = vendorRepository.findById(listItemRequest.getVendorId()).get();
        if(vendor == null)
        {
            throw new VendorNotFoundException("Invalid Vendor Id !!!!");
        }

        ProductItem productItem = ProductItemTransformer.fromProductRequestToProductItem(listItemRequest.getProductRequest());

        ListItem listItem = productItem.getListItem();
        listItem.setAvailable(true);

        ListItem savedListItem = listItemRepository.save(listItem);

        ProductItem savedProductItem = producttItemRepository.save(productItem);

        return ProductItemTransformer.fromProductItemToProductItemResponse(savedProductItem);
    }

    @Override
    public String deleteProduct(int id) {
        ProductItem productItem = producttItemRepository.findById(id).get();
        if(productItem == null){
            throw new ProductItemNotFoundException("Invalid Product Id!!!");
        }
        Vendor vendor = productItem.getListItem().getVendor();
        List<ListItem> listItems = listItemRepository.findById(productItem.getListItem().getId()).stream().toList();

        listItems.remove(productItem);

        producttItemRepository.delete(productItem);
        return "Successfully deleted Product.";
    }

    @Override
    public ResponseEntity updateProduct(int id,ProductRequest productRequest) {
        ProductItem productItem = producttItemRepository.findById(id).get();

        if(productItem == null){
            throw new ProductItemNotFoundException("Invalid Product Id !!!");
        }

        productItem.setProductName(productRequest.getProductName());
        productItem.setRequiredQuantity(productRequest.getRequiredQuantity());
        productItem.setDiscription(productRequest.getDiscription());
        productItem.setCategory(productRequest.getCategory());
        productItem.setSubCategory(productRequest.getSubCategory());
        productItem.setPrice(productRequest.getPrice());

        ProductItem savedProductItem = producttItemRepository.save(productItem);

        return new ResponseEntity("Successfully updated the product details.", HttpStatus.ACCEPTED);
    }


}
