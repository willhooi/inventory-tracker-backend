package com.simple_inventory_tracker.project.config;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.simple_inventory_tracker.project.entity.Product;
import com.simple_inventory_tracker.project.entity.Stock;
import com.simple_inventory_tracker.project.entity.Supplier;
import com.simple_inventory_tracker.project.repository.ProductRepository;
import com.simple_inventory_tracker.project.repository.StockRepository;
import com.simple_inventory_tracker.project.repository.SupplierRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final StockRepository stockRepository;

    public DataLoader(ProductRepository productRepository, 
                      SupplierRepository supplierRepository,
                      StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.stockRepository = stockRepository;
    }

    @PostConstruct
    public void loadData() {
        if (supplierRepository.count() > 0 || productRepository.count() > 0) {
            System.out.println(">>> Skipping DataLoader – data already exists.");
            return;
        }

        System.out.println(">>> Seeding demo data...");

        // Suppliers
        Supplier fruitSupplier = supplierRepository.save(Supplier.builder()
                .name("FreshFarm")
                .contactPerson("Linda Tan")
                .email("linda@freshfarm.com")
                .phone("61234567")
                .address("21 Pasir Panjang")
                .build());

        Supplier grocerySupplier = supplierRepository.save(Supplier.builder()
                .name("DailyGrocer")
                .contactPerson("Ali Rahman")
                .email("ali@dailygrocer.com")
                .phone("69874512")
                .address("5 Tanjong Pagar Rd")
                .build());

        // Products
        Product apple = productRepository.save(Product.builder()
                .name("Apple")
                .sku("APL-001")
                .description("Washington red apples")
                .price(1.90)
                .supplier(fruitSupplier)
                .build());

        Product banana = productRepository.save(Product.builder()
                .name("Banana")
                .sku("BAN-001")
                .description("Sweet yellow bananas")
                .price(1.20)
                .supplier(fruitSupplier)
                .build());

        Product orange = productRepository.save(Product.builder()
                .name("Orange")
                .sku("ORG-001")
                .description("Juicy navel oranges")
                .price(1.50)
                .supplier(fruitSupplier)
                .build());

        Product milk = productRepository.save(Product.builder()
                .name("Milk")
                .sku("MLK-001")
                .description("Fresh full cream milk (1L)")
                .price(2.80)
                .supplier(grocerySupplier)
                .build());

        Product bread = productRepository.save(Product.builder()
                .name("Bread")
                .sku("BRD-001")
                .description("Wholemeal sandwich loaf")
                .price(2.20)
                .supplier(grocerySupplier)
                .build());

        // Stocks
        stockRepository.save(Stock.builder()
                .product(apple)
                .quantityOnHand(50)
                .reorderLevel(20)
                .lastUpdate(LocalDateTime.now())
                .build());

        stockRepository.save(Stock.builder()
                .product(banana)
                .quantityOnHand(30)
                .reorderLevel(15)
                .lastUpdate(LocalDateTime.now())
                .build());

        stockRepository.save(Stock.builder()
                .product(milk)
                .quantityOnHand(25)
                .reorderLevel(10)
                .lastUpdate(LocalDateTime.now())
                .build());
    }
}
