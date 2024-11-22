package com.spring.products.repository;

import com.spring.products.entity.CPUEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface CPURepository extends JpaRepository<CPUEntity, UUID> {
    List<CPUEntity> findByBrandIgnoreCase(String brand);
    
    @Query("SELECT c FROM CPUEntity c WHERE c.cores >= :minCores")
    List<CPUEntity> findByCoresGreaterThanEqual(int minCores);
    
    List<CPUEntity> findByPriceLessThanEqual(double maxPrice);
}