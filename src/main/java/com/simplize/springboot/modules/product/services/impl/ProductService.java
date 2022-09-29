package com.simplize.springboot.modules.product.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.simplize.springboot.models.ResponseObject;
import com.simplize.springboot.modules.product.entities.Product;
import com.simplize.springboot.modules.product.repositories.ProductRepository;
import com.simplize.springboot.modules.product.services.IProductService;

@Service
public class ProductService implements IProductService {
    //DI = Dependency Injection
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<ResponseObject> getProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "Query product successfully", products)
            //you can replace "ok" with your defined "error code"
        );
    }

    public ResponseEntity<ResponseObject> getProductById(Long id) {
        Optional<Product> foundProduct = productRepository.findById(id);
        if (!foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find product with id = "+id, "")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "Query product successfully", foundProduct)
            //you can replace "ok" with your defined "error code"
        );
    }

    public ResponseEntity<ResponseObject> addProduct(Product product) {
        //2 products must not have the same name !
        List<Product> foundProducts = productRepository.findByProductName(product.getProductName().trim());
        if(foundProducts.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("failed", "Product name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
           new ResponseObject("ok", "Insert Product successfully", productRepository.save(product))
        );
    }

    public ResponseEntity<ResponseObject> updateProduct(Long id, Product product) {
        Product updatedProduct = productRepository.findById(id)
                .map(productItem -> {
                    productItem.setProductName(product.getProductName());
                    productItem.setYear(product.getYear());
                    productItem.setPrice(product.getPrice());
                    return productRepository.save(product);
                }).orElseGet(() -> {
                    product.setId(id);
                    return productRepository.save(product);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update Product successfully", updatedProduct)
        );
    }

    public ResponseEntity<ResponseObject> deleteProduct(Long id) {
        Boolean exists = productRepository.existsById(id);
        if(exists) {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", "Cannot find product to delete", "")
        );
    }
}
