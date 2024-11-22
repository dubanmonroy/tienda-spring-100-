package com.spring.products.repository;

import com.spring.products.entity.MotherboardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface MotherboardRepository extends JpaRepository<MotherboardEntity, UUID> {
    List<MotherboardEntity> findBySocketType(String socketType);
    
    @Query("SELECT m FROM MotherboardEntity m WHERE m.formFactor = :formFactor")
    List<MotherboardEntity> findByFormFactor(String formFactor);
    
    List<MotherboardEntity> findByChipsetContainingIgnoreCase(String chipset);
}