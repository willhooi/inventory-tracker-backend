package com.simple_inventory_tracker.project.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple_inventory_tracker.project.entity.Product;
import com.simple_inventory_tracker.project.entity.Stock;
import com.simple_inventory_tracker.project.exception.ProductNotFoundException;
import com.simple_inventory_tracker.project.exception.StockAlreadyExistsException;
import com.simple_inventory_tracker.project.exception.StockNotFoundException;
import com.simple_inventory_tracker.project.exception.StockQuantityInsufficientException;
import com.simple_inventory_tracker.project.notification.NotificationService;
import com.simple_inventory_tracker.project.repository.ProductRepository;
import com.simple_inventory_tracker.project.repository.StockRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StockServiceImpl implements StockService {
    private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    @Autowired
    private NotificationService notificationService;

    public StockServiceImpl(StockRepository stockRepository, ProductRepository productRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Stock createStock(Long productId, Stock stock) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        // Check if product already has stock
        if (product.getStock() != null) {
            String errorMsg = "Stock already exists for product '" + product.getName() + "' (id: " + productId + ")";
            logger.error("CREATE ERROR: {}", errorMsg);
            throw new StockAlreadyExistsException(product.getName(), productId);
        }

        stock.setProduct(product);
        stock.setLastUpdate(LocalDateTime.now());

        logger.info("CREATE: Stock created for product '{}', quantity: {}",
                product.getName(), stock.getQuantityOnHand());

        return stockRepository.save(stock);
    }

    @Override
    public Stock getStock(Long id) {
        logger.info("GET: Fetching stock by id {}", id);
        return stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException(id));
    }

    @Override
    public List<Stock> getAllStocks() {
        logger.info("GET: Fetching all stock entries");
        return stockRepository.findAll();
    }

    @Override
    public Stock updateStock(Long id, Stock updatedStock) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException(id));

        int incrementAmount = updatedStock.getQuantityOnHand();
        stock.setQuantityOnHand(stock.getQuantityOnHand() + incrementAmount);
        stock.setReorderLevel(updatedStock.getReorderLevel());
        stock.setLastUpdate(LocalDateTime.now());

        logger.info("UPDATE: Stock [id: {}] for product '{}' increased by {}, new qty: {}, reorder level: {}",
                stock.getId(), stock.getProduct().getName(), incrementAmount, stock.getQuantityOnHand(),
                stock.getReorderLevel());

        return stockRepository.save(stock);
    }

    @Override
    public void deleteStock(Long id) {
        logger.info("❌ DELETE: Deleting stock id {}", id);
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException(id));

        Product product = stock.getProduct();
        product.setStock(null); // unlink
        productRepository.save(product);
        stockRepository.deleteById(id);
    }

    @Override
    public Stock adjustStockQuantity(Long id, Integer adjQuantity) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException(id));

        int newQuantity = stock.getQuantityOnHand() + adjQuantity;

        if (newQuantity < 0) {
            throw new StockQuantityInsufficientException(id);
        }

        stock.setQuantityOnHand(newQuantity);
        stock.setLastUpdate(LocalDateTime.now());
        stockRepository.save(stock);

        logger.info("✅ ADJUST: Stock [id: {}] for product '{}' adjusted by {}. New qty: {}",
                stock.getId(), stock.getProduct().getName(), adjQuantity, newQuantity);

        if (newQuantity == 0) {
            logger.warn("🚨ZERO STOCK: Stock for '{}' [id: {}] has reached ZERO. Sending urgent notification.",
                    stock.getProduct().getName(), stock.getId());
            notificationService.sendNotification(
                    stock.getProduct().getName() + " stock has reached ZERO!");
        } else if (stock.getReorderLevel() != null && newQuantity < stock.getReorderLevel()) {
            logger.warn("📋REORDER: Stock for '{}' [id: {}] is below reorder level ({}). Sending notification.",
                    stock.getProduct().getName(), stock.getId(), stock.getReorderLevel());
            notificationService.sendNotification(stock.getProduct().getName());
        }

        return stock;
    }
}
