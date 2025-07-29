package com.simple_inventory_tracker.project.service;

import java.util.List;

import com.simple_inventory_tracker.project.entity.Product;

public interface ProductService {
    Product createProduct( Product product);

    Product getProduct(Long id);

    List<Product> getAllProducts();

    List<Product> findByName(String name);
    
}
