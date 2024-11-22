package com.spring.products.repository;

import com.spring.products.entity.RAMEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface RAMRepository extends JpaRepository<RAMEntity, UUID> {
    List<RAMEntity> findByCapacity(int capacity);
    
    @Query("SELECT r FROM RAMEntity r WHERE r.speed >= :minSpeed")
    List<RAMEntity> findByMinimumSpeed(int minSpeed);
    
    List<RAMEntity> findByTypeIgnoreCase(String type);
}