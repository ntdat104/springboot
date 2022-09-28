package com.simplize.springboot.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplize.springboot.product.models.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductName(String productName);
}
