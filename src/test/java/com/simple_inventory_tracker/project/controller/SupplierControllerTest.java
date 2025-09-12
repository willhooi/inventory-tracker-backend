package com.simple_inventory_tracker.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple_inventory_tracker.project.entity.Supplier;
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
public class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    @DisplayName("GET /suppliers/{id} should return supplier")
    public void getSupplierByIdTest() throws Exception {
        // 1. Create and save supplier
        Supplier supplier = new Supplier();
        supplier.setName("Tech Supplier Ltd");
        supplier.setContactPerson("Alice Chen");
        supplier.setEmail("alice@techsupplier.com");
        supplier.setPhone("87654321");
        supplier.setAddress("789 Tech Avenue");
        Supplier savedSupplier = supplierRepository.save(supplier);

        // 2. Build the request
        RequestBuilder request = MockMvcRequestBuilders
                .get("/suppliers/" + savedSupplier.getId());

        // 3. Perform request, get response & assert
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(savedSupplier.getId()))
                .andExpect(jsonPath("$.name").value("Tech Supplier Ltd"))
                .andExpect(jsonPath("$.contactPerson").value("Alice Chen"))
                .andExpect(jsonPath("$.email").value("alice@techsupplier.com"))
                .andExpect(jsonPath("$.phone").value("87654321"))
                .andExpect(jsonPath("$.address").value("789 Tech Avenue"));
    }
}