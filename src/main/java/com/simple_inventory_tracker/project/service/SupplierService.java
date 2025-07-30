package com.simple_inventory_tracker.project.service;

import java.util.List;

import com.simple_inventory_tracker.project.entity.Supplier;

public interface SupplierService {
    
    Supplier createSupplier(Supplier supplier);

    Supplier getSupplier(Long id);

    List<Supplier> getAllSuppliers();

    Supplier updateSupplier(Long id, Supplier supplier);

    void deleteSupplier(Long id);

    List<Supplier> findByName(String name);

    
}
