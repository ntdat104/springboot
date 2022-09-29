package com.simplize.springboot.modules.product.services;

import org.springframework.http.ResponseEntity;

import com.simplize.springboot.models.ResponseObject;
import com.simplize.springboot.modules.product.entities.Product;

public interface IProductService {
    ResponseEntity<ResponseObject> getProducts();
    ResponseEntity<ResponseObject> getProductById(Long id);
    ResponseEntity<ResponseObject> addProduct(Product product);
    ResponseEntity<ResponseObject> updateProduct(Long id, Product product);
    ResponseEntity<ResponseObject> deleteProduct(Long id);
}
