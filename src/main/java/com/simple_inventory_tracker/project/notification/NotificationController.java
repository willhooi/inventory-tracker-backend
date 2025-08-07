package com.simple_inventory_tracker.project.notification;

import com.simple_inventory_tracker.project.entity.Stock;
import com.simple_inventory_tracker.project.repository.StockRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final StockRepository stockRepository;

    public NotificationController(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @GetMapping("/low-stock")
    public List<Stock> getLowStockItems() {
        return stockRepository.findAll()
            .stream()
            .filter(stock -> stock.getReorderLevel() != null &&
                             stock.getQuantityOnHand() < stock.getReorderLevel())
            .collect(Collectors.toList());
    }
}
