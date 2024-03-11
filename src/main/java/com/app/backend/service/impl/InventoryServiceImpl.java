package com.app.backend.service.impl;

// InventoryServiceImpl.java
import com.app.backend.entity.EmployeeEntity;
import com.app.backend.entity.InventoryEntity;
import com.app.backend.repository.InventoryRepository;
import com.app.backend.service.InventoryService;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;

    public InventoryServiceImpl( InventoryRepository inventoryRepository){
        this.inventoryRepository =inventoryRepository;
    }

    @Override
    public InventoryEntity getInventoryBySku(Long sku) {
        return inventoryRepository.findBySku(sku);
    }

    @Override
    public List<InventoryEntity> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public InventoryEntity addInventory(InventoryEntity inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public InventoryEntity updateInventory(InventoryEntity inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    @Transactional
    public void deleteInventoryBySku(Long sku) {
        try {
            inventoryRepository.deleteById(sku);
        } catch (Exception e) {
            throw new RuntimeException("No se puede eliminar el artículo del inventario. Está siendo referenciado por una o más pólizas.");
        }
    }
}
