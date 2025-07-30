package com.simple_inventory_tracker.project.config;

import org.springframework.stereotype.Component;

import com.simple_inventory_tracker.project.entity.Product;
import com.simple_inventory_tracker.project.repository.ProductRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {
    private final ProductRepository productRepository;

    public DataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void loadData() {
        // productRepository.deleteAll();

        productRepository.save(Product.builder()
                .name("Apple")
                .sku("APL-001")
                .description("Washington red apples")
                .price(1.90)
                .build());

        productRepository.save(Product.builder()
                .name("Banana")
                .sku("BAN-001")
                .description("Sweet yellow bananas")
                .price(1.20)
                .build());

        productRepository.save(Product.builder()
                .name("Orange")
                .sku("ORG-001")
                .description("Juicy navel oranges")
                .price(1.50)
                .build());

        productRepository.save(Product.builder()
                .name("Milk")
                .sku("MLK-001")
                .description("Fresh full cream milk (1L)")
                .price(2.80)
                .build());

        productRepository.save(Product.builder()
                .name("Bread")
                .sku("BRD-001")
                .description("Wholemeal sandwich loaf")
                .price(2.20)
                .build());

    }

}
