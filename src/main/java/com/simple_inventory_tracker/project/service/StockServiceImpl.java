package com.simple_inventory_tracker.project.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.simple_inventory_tracker.project.entity.Stock;
import com.simple_inventory_tracker.project.exception.StockNotFoundException;
import com.simple_inventory_tracker.project.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock createStock(Stock stock) {
        stock.setLastUpdate(LocalDateTime.now());
        return stockRepository.save(stock);
    }

    @Override
    public Stock getStock(Long id) {
        return stockRepository.findById(id).orElseThrow(() -> new StockNotFoundException(id));

    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock updateStock(Long id, Stock updatedStock) {
        Stock stock = getStock(id);
        stock.setQuantityOnHand(updatedStock.getQuantityOnHand());
        stock.setReorderLevel(updatedStock.getReorderLevel());
        stock.setLastUpdate(LocalDateTime.now());
        return stockRepository.save(stock);
    }

    @Override
    public Stock adjustQuantity(Long id, int change) {
        Stock stock = getStock(id);
        int before = stock.getQuantityOnHand();
        int after = before + change;

        System.out.println("Adjusting stock ID " + id + ": " + before + " → " + after);

        stock.setQuantityOnHand(after);
        stock.setLastUpdate(LocalDateTime.now());
        return stockRepository.save(stock);
    }

}
