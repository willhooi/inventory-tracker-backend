package com.simple_inventory_tracker.project.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id){
        super("Could not find product with id: "+ id);
    }
    
}
