package com.app.backend.controller;

import com.app.backend.DTO.ResponseUtilDTO;
import com.app.backend.entity.InventoryEntity;
import com.app.backend.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ResponseUtilDTO.Response<Object>> getInventoryBySku(@PathVariable Long sku) {
        try {
            logger.info("Fetching inventory by SKU: {}", sku);
            InventoryEntity inventory = inventoryService.getInventoryBySku(sku);

            if (inventory == null) {
                logger.warn("Inventory not found for SKU: {}", sku);
                return ResponseUtilDTO.generateErrorResponse("Inventario no encontrado");
            }

            logger.info("Inventory found for SKU: {}", sku);
            return ResponseUtilDTO.generateSuccessResponse(inventory);

        } catch (Exception e) {
            logger.error("Error occurred while fetching inventory by SKU: {}", sku, e);
            return ResponseUtilDTO.generateErrorResponse(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseUtilDTO.Response<Object>> getAllInventory() {
        try {
            logger.info("Fetching all inventory");
            List<InventoryEntity> inventoryList = inventoryService.getAllInventory();

            if (inventoryList.isEmpty()) {
                logger.warn("No inventory available");
                return ResponseUtilDTO.generateErrorResponse("No hay inventario");
            }

            logger.info("Fetched {} inventory items", inventoryList.size());
            return ResponseUtilDTO.generateSuccessResponse(inventoryList);

        } catch (Exception e) {
            logger.error("Error occurred while fetching all inventory", e);
            return ResponseUtilDTO.generateErrorResponse(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseUtilDTO.Response<Object>> addInventory(@RequestBody InventoryEntity inventory) {
        try {
            logger.info("Adding new inventory: {}", inventory);
            InventoryEntity addedInventory = inventoryService.addInventory(inventory);
            logger.info("Inventory added successfully with SKU: {}", addedInventory.getSku());
            return ResponseUtilDTO.generateSuccessResponse(addedInventory);
        } catch (Exception e) {
            logger.error("Error occurred while adding new inventory", e);
            return ResponseUtilDTO.generateErrorResponse(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseUtilDTO.Response<Object>> updateInventory(@RequestBody InventoryEntity inventory) {
        try {
            logger.info("Updating inventory with SKU: {}", inventory.getSku());
            InventoryEntity inventoryUpdated = inventoryService.updateInventory(inventory);
            logger.info("Inventory updated successfully with SKU: {}", inventoryUpdated.getSku());
            return ResponseUtilDTO.generateSuccessResponse("Se actualizó correctamente el inventario # " + inventoryUpdated.getSku());
        } catch (Exception e) {
            logger.error("Error occurred while updating inventory", e);
            return ResponseUtilDTO.generateErrorResponse(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{sku}")
    public ResponseEntity<ResponseUtilDTO.Response<Object>> deleteInventoryBySku(@PathVariable Long sku) {
        try {
            logger.info("Deleting inventory with SKU: {}", sku);
            inventoryService.deleteInventoryBySku(sku);
            logger.info("Inventory deleted successfully with SKU: {}", sku);
            return ResponseUtilDTO.generateSuccessResponse("Se eliminó correctamente el inventario # " + sku);
        } catch (Exception e) {
            logger.error("Error occurred while deleting inventory with SKU: {}", sku, e);
            if(e.getClass().getName().equals("org.springframework.dao.DataIntegrityViolationException")){
                return ResponseUtilDTO.generateErrorResponse("Error al intentar un item con una clave foranea");

            }
            return ResponseUtilDTO.generateErrorResponse( e.getMessage());
        }
    }
}
