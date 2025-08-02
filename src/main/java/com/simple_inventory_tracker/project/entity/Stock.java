package com.simple_inventory_tracker.project.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockId")
    private Long id;

    @OneToOne(optional=false)
    @JoinColumn(name = "product_id", unique = true)
    @JsonManagedReference
    private Product product;

    @Column(name = "stockQuantity")
    @Min(value = 1, message = "Stock quantitiy should be greater than zero")
    private Integer quantityOnHand;

    @Column(name = "stockReorderLevel")
    @Min(value = 1, message = "Stock quantitiy should be greater than zero")
    private Integer reorderLevel;

    @Column(name = "DateTime")
    private LocalDateTime lastUpdate;
    
}
