package com.simple_inventory_tracker.project.exception;

public class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(Long id){
        super("Could not find supplier with id: "+ id);
    }
    
}
