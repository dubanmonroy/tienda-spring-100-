package com.spring.products.repository;

import com.spring.products.entity.PSUEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface PSURepository extends JpaRepository<PSUEntity, UUID> {
    List<PSUEntity> findByWattageGreaterThanEqual(int minWattage);
    
    @Query("SELECT p FROM PSUEntity p WHERE p.efficiency = :efficiency")
    List<PSUEntity> findByEfficiencyRating(String efficiency);
    
    List<PSUEntity> findByModularTypeIgnoreCase(String modularType);
}