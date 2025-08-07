package com.simple_inventory_tracker.project.exception;

public class StockAlreadyExistsException extends RuntimeException {
    public StockAlreadyExistsException(String productName, Long productId) {
        super("Stock already exists for product '" + productName + "' (id: " + productId + ")");
    }
}
