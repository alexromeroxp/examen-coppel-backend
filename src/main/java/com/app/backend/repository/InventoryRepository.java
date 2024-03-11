package com.app.backend.repository;

// InventoryRepository.java
import com.app.backend.entity.EmployeeEntity;
import com.app.backend.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    InventoryEntity findBySku(Long sku);

    // spring provides all the basic crud operations. No Coding!
}

