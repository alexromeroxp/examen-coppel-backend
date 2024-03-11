package com.app.backend.repository;

import com.app.backend.entity.InventoryEntity;
import com.app.backend.entity.PolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<PolicyEntity, Long> {

    <S extends PolicyEntity> S save(S entity);
    // spring provides all the basic crud operations. No Coding!
}

