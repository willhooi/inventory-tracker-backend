package com.simple_inventory_tracker.project.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.simple_inventory_tracker.project.entity.Product;
import com.simple_inventory_tracker.project.entity.Supplier;
import com.simple_inventory_tracker.project.exception.ProductNotFoundException;
import com.simple_inventory_tracker.project.repository.ProductRepository;
import com.simple_inventory_tracker.project.repository.SupplierRepository;

@Primary
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public ProductServiceImpl(ProductRepository productRepository, SupplierRepository supplierRepository) {
        System.out.println(">>> ProductServiceImpl initialized!");
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    // Create
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Read
    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    // Read all
    @Override
    public List<Product> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product productToUpdate = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setSku(product.getSku());

        // update supplier
        if (product.getSupplier() != null) {
            Long supplierId = product.getSupplier().getId();
            Supplier existingSupplier = supplierRepository.findById(supplierId)
                    .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + supplierId));
            productToUpdate.setSupplier(existingSupplier);
        }

        return productRepository.save(productToUpdate);
    }

    @Override
    public Product updateSupplier(Long productId, Supplier supplier) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        if (supplier == null || supplier.getId() == null) {
            throw new IllegalArgumentException("Supplier ID must be provided.");
        }

        Supplier existingSupplier = supplierRepository.findById(supplier.getId())
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + supplier.getId()));

        product.setSupplier(existingSupplier);
        return productRepository.save(product);
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> foundProduct = productRepository.findByName(name);
        return foundProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
