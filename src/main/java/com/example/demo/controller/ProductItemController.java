package com.example.demo.controller;

import com.example.demo.Enum.Category;
import com.example.demo.dto.response.ProductItemResponsewithVendorName;
import com.example.demo.model.Comment;
import com.example.demo.service.ProductItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-item")
public class ProductItemController {

    private final ProductItemService productItemService;


    public ProductItemController(ProductItemService productItemService) {
        this.productItemService = productItemService;
    }

    @GetMapping("/get-product-from-particular-category")
    public ResponseEntity getProductFromCategory (@RequestParam("category")Category category){
        try{
            List<ProductItemResponsewithVendorName> response = productItemService.getProductFromCategory(category);
            return new ResponseEntity(response, HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{productId}/comments")
    public ResponseEntity addCommentToProduct(@PathVariable int productId, @RequestBody Comment comment){
        productItemService.addCommentToProduct(productId,comment);
        return new ResponseEntity("Comment is added.",HttpStatus.CREATED);
    }


}
