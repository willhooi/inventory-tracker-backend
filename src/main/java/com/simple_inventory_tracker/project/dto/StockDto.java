package com.simple_inventory_tracker.project.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StockDto {
    private Long id;
    private Long productId;
    private String productName;
    private int quantityOnHand;
    private int reorderLevel;
    private LocalDateTime lastUpdate;
    
}
