package com.spring.products.repository;

import com.spring.products.entity.StorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface StorageRepository extends JpaRepository<StorageEntity, UUID> {
    List<StorageEntity> findByType(String type);
    
    @Query("SELECT s FROM StorageEntity s WHERE s.capacity >= :minCapacity")
    List<StorageEntity> findByMinimumCapacity(int minCapacity);
    
    List<StorageEntity> findByFormFactorIgnoreCase(String formFactor);
}