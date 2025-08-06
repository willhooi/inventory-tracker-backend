package com.simple_inventory_tracker.project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple_inventory_tracker.project.entity.Product;
import com.simple_inventory_tracker.project.entity.Stock;
import com.simple_inventory_tracker.project.exception.ProductNotFoundException;
import com.simple_inventory_tracker.project.exception.StockNotFoundException;
import com.simple_inventory_tracker.project.exception.StockQuantityInsufficientException;
import com.simple_inventory_tracker.project.notification.NotificationService;
import com.simple_inventory_tracker.project.repository.ProductRepository;
import com.simple_inventory_tracker.project.repository.StockRepository;

import org.hibernate.annotations.TimeZoneStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StockServiceImpl implements StockService {
    private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    private StockRepository stockRepository;
    private ProductRepository productRepository;

    @Autowired
    private NotificationService notificationService;

    public StockServiceImpl(StockRepository stockRepository, ProductRepository productRepository) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
    }

    // Creating initial stock with product Id
    @Override
    public Stock createStock(Long productId, Stock stock) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException(productId));
        stock.setProduct(product);
        stock.setLastUpdate(LocalDateTime.now());
        logger.info("CREATE: Stock initialization for product id: "+ product.getId() + 
                    ", product name: " + product.getName() + " has been created.");
        return stockRepository.save(stock);  
    }

      // View the particular stock status with specific product id
    @Override
    public Stock getStock(Long id) {  
        logger.info("GET: View stock id "+ id + " info."); 
        return stockRepository.findById(id).orElseThrow(() -> new StockNotFoundException(id));
    }

    // View all the stocks status
    @Override
    public List<Stock> getAllStocks() {
        logger.info("GET: View all stocks info.");
        return stockRepository.findAll();
    }

    // Update the incoming stock quantity and stock balance
    @Override
    public Stock updateStock(Long id, Stock updatedStock) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new StockNotFoundException(id));
        // update the stock balance = refill quantitiy + original quantity
        stock.setQuantityOnHand(updatedStock.getQuantityOnHand() + stock.getQuantityOnHand());
        stock.setReorderLevel(updatedStock.getReorderLevel());
        stock.setLastUpdate(LocalDateTime.now());
        logger.info("UPDATE: Stock in quantity[stock id: " + id + ", product: " + 
                   stock.getProduct().getName() + " ] = "+ updatedStock.getQuantityOnHand());
        return stockRepository.save(stock);
    }

    @Override
    public void delectStock(Long id){
        logger.info("DELETE: stock id "+ id + " info has been deleted.");
        Stock stock = stockRepository.findById(id).orElseThrow(()->new StockNotFoundException(id));
        Long productId = stock.getProduct().getId();
        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException(productId));
        product.setStock(null);
        productRepository.save(product);
        stockRepository.deleteById(id);

    }

    // Update the outgoing stock quantity and stock balance
    // Check the stock balance and notify to the stock keeper if the stock is required to reorder.
    @Override
    public Stock deleteStockQuantity(Long id, Integer adjQuantity) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new StockNotFoundException(id));
        // Check if stock balance is enough 
         if(isSufficientQuantity(stock, adjQuantity)){
            updateStockQuantityAdjustment(stock, adjQuantity);
            checkReorderLevel(stock, adjQuantity); 
            logger.info("DELETEQTY: Stock out quantity[stock id: " + id + ", product: " + 
                   stock.getProduct().getName() + " ] = "+ adjQuantity);
            return stock;
        }
    
        throw new StockQuantityInsufficientException(id);
   
    }

    public boolean isSufficientQuantity(Stock stock, Integer quantity){

        boolean ret = true;
        if(stock.getQuantityOnHand() < quantity){
            ret = false;
        }
        return ret;
     }

     public void checkReorderLevel(Stock stock,  Integer quantity){
        Product product = stock.getProduct();
        if(stock.getQuantityOnHand() < stock.getReorderLevel()){
            logger.warn("DELETE: Stock quantity[stock id: " + stock.getId() + ", product: " + 
                        stock.getProduct().getName() + "] is lower than reorder level.");
            notificationService.sendNotification(product.getName());
         }
     }

     public void updateStockQuantityAdjustment(Stock stock, Integer quantity){
        Integer stockBalance = stock.getQuantityOnHand() - quantity;
        stock.setQuantityOnHand(stockBalance);
        stock.setLastUpdate(LocalDateTime.now());
        stockRepository.save(stock);

     }

}
