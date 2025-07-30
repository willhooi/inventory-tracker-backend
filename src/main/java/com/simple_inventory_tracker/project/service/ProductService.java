package com.simple_inventory_tracker.project.service;

import java.util.List;

import com.simple_inventory_tracker.project.entity.Product;
import com.simple_inventory_tracker.project.entity.Supplier;

public interface ProductService {
    Product createProduct( Product product);

    Product getProduct(Long id);

    List<Product> getAllProducts();

    Product updateProduct(Long id, Product product);

    Product updateSupplier(Long productId, Supplier supplier);

    void deleteProduct(Long id);

    List<Product> findByName(String name);
    
}
