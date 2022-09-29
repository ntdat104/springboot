package com.simplize.springboot.modules.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplize.springboot.modules.product.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductName(String productName);
}
