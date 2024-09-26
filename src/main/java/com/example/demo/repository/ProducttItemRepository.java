package com.example.demo.repository;

import com.example.demo.Enum.Category;
import com.example.demo.model.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProducttItemRepository extends JpaRepository<ProductItem,Integer> {
    List<ProductItem> findByCategory(Category category);

    List<ProductItem> findByPrice(int price);
}
