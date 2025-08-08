package com.simple_inventory_tracker.project.service;

import static org.mockito.ArgumentMatchers.nullable;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.simple_inventory_tracker.project.entity.Product;
import com.simple_inventory_tracker.project.entity.Stock;
import com.simple_inventory_tracker.project.exception.StockQuantityInsufficientException;
import com.simple_inventory_tracker.project.repository.StockRepository;

@ExtendWith(MockitoExtension.class)
public class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    StockServiceImpl stockService;

    @Test
    public void getStockTest() {
        Stock stock = Stock.builder().quantityOnHand(20).reorderLevel(10).build();
        Long stockId = 1L;
        Mockito.when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        Stock stockResult = stockService.getStock(stockId);

        Assertions.assertEquals(stock, stockResult, "The result should be matched.");
    }

    @Test
    public void stockInsufficientTest() {
        Product product = Product.builder().name("Test Product").build();
        Stock stock = Stock.builder()
                .quantityOnHand(10)
                .reorderLevel(5)
                .product(product)
                .build();
        Long stockId = 1L;

        Mockito.when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        Assertions.assertThrows(StockQuantityInsufficientException.class, () -> {
            stockService.adjustStockQuantity(stockId, -12); 
        });
    }

}
