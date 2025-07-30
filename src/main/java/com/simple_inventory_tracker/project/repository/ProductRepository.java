package com.simple_inventory_tracker.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simple_inventory_tracker.project.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);
    
    
}
