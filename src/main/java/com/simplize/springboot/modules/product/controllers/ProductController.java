package com.simplize.springboot.modules.product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplize.springboot.models.ResponseObject;
import com.simplize.springboot.modules.product.entities.Product;
import com.simplize.springboot.modules.product.services.impl.ProductService;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    ResponseEntity<ResponseObject> getAllProducts() {
       return productService.getProducts();
    }

    @GetMapping(path = "{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping(path = "{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping(path = "{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
