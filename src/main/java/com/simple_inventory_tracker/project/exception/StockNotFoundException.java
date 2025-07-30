package com.simple_inventory_tracker.project.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(Long id){
        super("Could not find stock with id: " + id);
    }
    
}
