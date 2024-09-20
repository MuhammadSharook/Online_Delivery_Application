package com.example.demo.repository;

import com.example.demo.model.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducttItemRepository extends JpaRepository<ProductItem,Integer> {
}
