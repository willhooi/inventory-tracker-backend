package com.simple_inventory_tracker.project.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.simple_inventory_tracker.project.exception.SupplierNotFoundException;
import com.simple_inventory_tracker.project.repository.SupplierRepository;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceImplTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Test
    @DisplayName("Should throw SupplierNotFoundException when supplier not found")
    public void getSupplierNotFoundTest() {
        Long supplierId = 999L;
        Mockito.when(supplierRepository.findById(supplierId))
               .thenReturn(Optional.empty());

        Assertions.assertThrows(SupplierNotFoundException.class, () -> {
            supplierService.getSupplier(supplierId);
        });

        Mockito.verify(supplierRepository).findById(supplierId);
    }
}