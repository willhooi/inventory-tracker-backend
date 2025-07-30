package com.simple_inventory_tracker.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simple_inventory_tracker.project.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByProductId(Long productId);
    
}
