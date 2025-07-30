package com.simple_inventory_tracker.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "productId")
    private Long id;
    @Column(name= "productName")
    private String name;
    @Column(name= "productDescription")
    private String description;
    @Column(name= "productPrice")
    private double price;
    @Column(name="productSku", unique = true, nullable = false)
    private String sku;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    // @OneToOne(mappedBy = "product", cascade = CascadeType.ALL);
    // private Stock stock;
    
}
