package com.example.demo.service.Implementation;

import com.example.demo.Enum.Category;
import com.example.demo.dto.response.ProductItemResponsewithVendorName;
import com.example.demo.exception.ProductItemNotFoundException;
import com.example.demo.model.Comment;
import com.example.demo.model.ProductItem;
import com.example.demo.repository.ProducttItemRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.ProductItemService;
import com.example.demo.transformer.ProductItemTransformer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductItemServiceImpl implements ProductItemService {

    private final ProducttItemRepository producttItemRepository;
    private final VendorRepository vendorRepository;

    public ProductItemServiceImpl(ProducttItemRepository producttItemRepository,
                                  VendorRepository vendorRepository) {
        this.producttItemRepository = producttItemRepository;
        this.vendorRepository = vendorRepository;
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


}
