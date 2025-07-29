package com.simple_inventory_tracker.project.config;

import com.simple_inventory_tracker.project.entity.Product;
import com.simple_inventory_tracker.project.repository.ProductRepository;

import jakarta.annotation.PostConstruct;

public class DataLoader {
    private ProductRepository productRepository;

    public DataLoader(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void loadData(){
       // productRepository.deleteAll();

       productRepository.save(Product.builder()
       .name("apple")
       .description("Washington red apples")
       .price(1.90)
       .build());

    }

}
