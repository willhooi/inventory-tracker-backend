package com.simple_inventory_tracker.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message= "Product name required")
    @Column(name= "productName")
    private String name;

    @Column(name= "productDescription")
    private String description;

    @DecimalMin(value= "0.01", inclusive= true, message= "Price must be at least 0.01" )
    @Column(name= "productPrice")
    private double price;

    @NotBlank(message = "SKU is required")
    @Column(name="productSku", unique = true, nullable = false)
    private String sku;

    @NotNull(message = "Supplier required")
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonBackReference
    private Stock stock;
    
}
