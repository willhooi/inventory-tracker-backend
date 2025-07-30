package com.simple_inventory_tracker.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simple_inventory_tracker.project.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByName(String name); // exact match

    List<Supplier> findByNameContainingIgnoreCase(String keyword); 

}
