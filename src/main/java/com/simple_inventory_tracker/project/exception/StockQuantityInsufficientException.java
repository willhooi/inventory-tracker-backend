package com.simple_inventory_tracker.project.exception;

public class StockQuantityInsufficientException extends RuntimeException{
    public StockQuantityInsufficientException(Long id){
        super("Stock id "+ id +" balance is insufficient.");
    }
    
}
