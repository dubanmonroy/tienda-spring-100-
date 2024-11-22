package com.spring.products.repository;

import com.spring.products.entity.GPUEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface GPURepository extends JpaRepository<GPUEntity, UUID> {
    List<GPUEntity> findByManufacturerIgnoreCase(String manufacturer);
    
    @Query("SELECT g FROM GPUEntity g WHERE g.vramSize >= :minVram")
    List<GPUEntity> findByMinimumVRAM(int minVram);
    
    List<GPUEntity> findByPriceBetween(double minPrice, double maxPrice);
}