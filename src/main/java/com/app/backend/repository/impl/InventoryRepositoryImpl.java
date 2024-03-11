package com.app.backend.repository.impl;


import com.app.backend.entity.InventoryEntity;
import com.app.backend.repository.InventoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
@Component
public abstract class InventoryRepositoryImpl implements InventoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public InventoryRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public InventoryEntity findBySku(Long sku) {
        return this.entityManager.find(InventoryEntity.class, sku);
    }

}
