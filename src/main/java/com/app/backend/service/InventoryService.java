package com.app.backend.service;

// InventoryService.java
import com.app.backend.entity.EmployeeEntity;
import com.app.backend.entity.InventoryEntity;

import java.util.List;

public interface InventoryService {
    InventoryEntity getInventoryBySku(Long sku);
    List<InventoryEntity> getAllInventory();
    InventoryEntity addInventory(InventoryEntity inventory);
    InventoryEntity updateInventory(InventoryEntity inventory);
    void deleteInventoryBySku(Long sku);
}
