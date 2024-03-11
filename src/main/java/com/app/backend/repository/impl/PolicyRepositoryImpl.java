package com.app.backend.repository.impl;

import com.app.backend.entity.PolicyEntity;
import com.app.backend.repository.PolicyRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
public abstract class PolicyRepositoryImpl implements PolicyRepository{


    @PersistenceContext
    private EntityManager entityManager;

    public PolicyRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public <S extends PolicyEntity> S save(S entity) {
        entityManager.persist(entity);
        return entity;
    }
}
