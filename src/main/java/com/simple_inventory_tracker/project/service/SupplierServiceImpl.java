package com.simple_inventory_tracker.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.simple_inventory_tracker.project.entity.Supplier;
import com.simple_inventory_tracker.project.exception.SupplierNotFoundException;
import com.simple_inventory_tracker.project.repository.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier createSupplier(Supplier supplier){
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier getSupplier(Long id){
        return supplierRepository.findById(id).orElseThrow(() -> new SupplierNotFoundException(id));
    }

    @Override
    public List<Supplier> getAllSuppliers(){
        return supplierRepository.findAll();
    }

    @Override
    public Supplier updateSupplier(Long id, Supplier updatedSupplier){
        Supplier existing = supplierRepository.findById(id).orElseThrow(() -> new SupplierNotFoundException(id));

        existing.setName(updatedSupplier.getName());
        existing.setContactPerson(updatedSupplier.getContactPerson());
        existing.setEmail(updatedSupplier.getEmail());
        existing.setPhone(updatedSupplier.getPhone());
        existing.setAddress(updatedSupplier.getAddress());

        return supplierRepository.save(existing);
    }

    @Override
    public void deleteSupplier(Long id){
        if (!supplierRepository.existsById(id)){
            throw new SupplierNotFoundException(id);
        }
        supplierRepository.deleteById(id);
    }

    @Override
    public List<Supplier> findByName(String name){
        return supplierRepository.findByName(name);
    }

    
}
