package com.example.demo.service;

import com.example.demo.Enum.Category;
import com.example.demo.dto.request.ListItemRequest;
import com.example.demo.dto.request.ProductRequest;
import com.example.demo.dto.response.ProductItemResponse;
import com.example.demo.dto.response.ProductItemResponsewithVendorName;
import com.example.demo.model.Comment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductItemService {
    List<ProductItemResponsewithVendorName> getProductFromCategory(Category category);

    void addCommentToProduct(int productId, Comment comment);

    ProductItemResponse addProductItem(ListItemRequest listItemRequest);

    String deleteProduct(int id);

    ResponseEntity updateProduct(int id,ProductRequest productRequest);

    List<ProductItemResponsewithVendorName> getProductFromPrice(int price);
}
