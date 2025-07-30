package com.simple_inventory_tracker.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple_inventory_tracker.project.entity.Supplier;
import com.simple_inventory_tracker.project.service.SupplierService;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
   
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService){
        this.supplierService = supplierService;
    }

    //Read all
    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers(){
        List<Supplier> allSuppliers = supplierService.getAllSuppliers();
        return new ResponseEntity<>(allSuppliers,HttpStatus.OK);
    }

    //Read one
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplier(@PathVariable Long id){
        Supplier supplier = supplierService.getSupplier(id);
        return new ResponseEntity<>(supplier, HttpStatus.OK);            
    }

    //Post
    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier){
        Supplier created = supplierService.createSupplier(supplier);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody Supplier updated){
        Supplier updatedSupplier = supplierService.updateSupplier(id, updated);
        return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSupplier(@PathVariable Long id){
        supplierService.deleteSupplier(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
