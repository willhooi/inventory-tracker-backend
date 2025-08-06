package com.simple_inventory_tracker.project.service;

import java.util.List;

import com.simple_inventory_tracker.project.entity.Stock;

public interface StockService {
    Stock createStock(Long productId, Stock stock);
    Stock getStock(Long id);
    List<Stock> getAllStocks();
    Stock updateStock(Long id, Stock stock);
    void delectStock(Long id);
    Stock deleteStockQuantity(Long id, Integer adjQuantity); 
    
}
