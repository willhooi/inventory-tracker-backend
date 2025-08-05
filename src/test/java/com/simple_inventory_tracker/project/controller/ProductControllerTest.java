package com.simple_inventory_tracker.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple_inventory_tracker.project.entity.Product;
import com.simple_inventory_tracker.project.entity.Supplier;
import com.simple_inventory_tracker.project.repository.ProductRepository;
import com.simple_inventory_tracker.project.repository.SupplierRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @DisplayName("GET /products/{id} should return product")
    @Test
    public void getProductByIdTest() throws Exception {
        // 1. Create and save supplier
        Supplier supplier = new Supplier();
        supplier.setName("Super Supplier");
        supplier.setContactPerson("Mr Lee");
        supplier.setEmail("lee@supersupplier.com");
        supplier.setPhone("12345678");
        supplier.setAddress("123 Supplier Road");
        Supplier savedSupplier = supplierRepository.save(supplier);

        // 2. Create and save product
        Product product = new Product();
        product.setName("Ice-cream");
        product.setSku("ICR-888"); 
        product.setDescription("Dark Chocolate ice-cream (1l)");
        product.setPrice(7.85);
        product.setSupplier(savedSupplier); 
        Product savedProduct = productRepository.save(product);

        // 3. Build the request
        RequestBuilder request = MockMvcRequestBuilders
                .get("/products/" + savedProduct.getId());

        // 4. Perform request, get response & assert
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(savedProduct.getId()))
                .andExpect(jsonPath("$.name").value("Ice-cream"))
                .andExpect(jsonPath("$.description").value("Dark Chocolate ice-cream (1l)"))
                .andExpect(jsonPath("$.sku").value("ICR-888"))
                .andExpect(jsonPath("$.price").value(7.85))
                .andExpect(jsonPath("$.supplier.name").value("Super Supplier"));
    }

    @Test
    @DisplayName("POST /products should create a valid product")
    public void validProductCreationTest() throws Exception {
        
        // 1. Create and save a supplier
        Supplier supplier = new Supplier();
        supplier.setName("Fruit Supplier");
        supplier.setContactPerson("Ah Seng");
        supplier.setEmail("seng@fruit.com");
        supplier.setPhone("98765432");
        supplier.setAddress("456 Orchard Lane");
        Supplier savedSupplier = supplierRepository.save(supplier);

        // 2. Create product object with supplier
        Product newProduct = Product.builder()
                .name("Durian")
                .description("Musang King 500g")
                .price(49.95)
                .sku("DUR-007")
                .supplier(savedSupplier) 
                .build();

        // 3. Convert the Java object to JSON
        String newProductAsJSON = objectMapper.writeValueAsString(newProduct);

        // 4. Build the request
        RequestBuilder request = MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newProductAsJSON);

        // 5. Perform request, get response and assert
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Durian"))
                .andExpect(jsonPath("$.description").value("Musang King 500g"))
                .andExpect(jsonPath("$.price").value(49.95))
                .andExpect(jsonPath("$.sku").value("DUR-007"))
                .andExpect(jsonPath("$.supplier.name").value("Fruit Supplier"));
    }

    @Test
    @DisplayName("POST /products with invalid fields")
    public void invalidProductCreationTest() throws Exception {
        
        // 1. Create invalid product (missing SKU)
        Product newProduct = Product.builder().name("Yoghurt")
        .description("").sku("").build();

        // 2. Convert Java object to JSON 
        String newProductAsJSON = objectMapper.writeValueAsString(newProduct);

        // 3. Build the request
        RequestBuilder request = MockMvcRequestBuilders.post("/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(newProductAsJSON);

        // 4. Perform request, get response and assert
        mockMvc.perform(request)
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

}
