package com.simple_inventory_tracker.project.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.simple_inventory_tracker.project.entity.Product;
import com.simple_inventory_tracker.project.exception.ProductNotFoundException;
import com.simple_inventory_tracker.project.repository.ProductRepository;

@Primary
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        System.out.println(">>> ProductServiceImpl initialized!");
        this.productRepository = productRepository;
    }

    //Create
    @Override
    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    //Read
    @Override
    public Product getProduct(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    //Read all
    @Override
    public List<Product> getAllProducts(){
        List<Product> allProducts = productRepository.findAll();
        return allProducts;
    }

    @Override
    public Product updateProduct(Long id, Product product){
        Product productToUpdate = productRepository.findById(id).orElseThrow( ()-> new ProductNotFoundException(id));

        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());

        return productRepository.save(productToUpdate);
    }

    @Override
    public List<Product> findByName(String name){
        List<Product> foundProduct = productRepository.findByName(name);
        return foundProduct;
    }

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


    
}
