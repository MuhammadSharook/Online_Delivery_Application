package com.example.demo.service;

import com.example.demo.Enum.Category;
import com.example.demo.dto.response.ProductItemResponsewithVendorName;
import com.example.demo.model.Comment;

import java.util.List;

public interface ProductItemService {
    List<ProductItemResponsewithVendorName> getProductFromCategory(Category category);

    void addCommentToProduct(int productId, Comment comment);
}
