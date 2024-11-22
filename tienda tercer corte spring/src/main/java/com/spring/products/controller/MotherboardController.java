package com.spring.products.controller;

import com.spring.products.entity.MotherboardEntity;
import com.spring.products.service.MotherboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/motherboards")
@Tag(name = "Motherboard", description = "Motherboard management endpoints")
public class MotherboardController {
    private final MotherboardService motherboardService;

    public MotherboardController(MotherboardService motherboardService) {
        this.motherboardService = motherboardService;
    }

    @PostMapping
    @Operation(summary = "Create a new motherboard")
    public ResponseEntity<MotherboardEntity> createMotherboard(@Valid @RequestBody MotherboardEntity motherboard) {
        return ResponseEntity.ok(motherboardService.save(motherboard));
    }

    @GetMapping
    @Operation(summary = "Get all motherboards")
    public List<MotherboardEntity> getAllMotherboards() {
        return motherboardService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get motherboard by ID")
    public ResponseEntity<MotherboardEntity> getMotherboardById(@PathVariable UUID id) {
        return motherboardService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/socket/{socketType}")
    @Operation(summary = "Get motherboards by socket type")
    public List<MotherboardEntity> getMotherboardsBySocket(@PathVariable String socketType) {
        return motherboardService.findBySocketType(socketType);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update motherboard")
    public ResponseEntity<MotherboardEntity> updateMotherboard(@PathVariable UUID id, @Valid @RequestBody MotherboardEntity motherboard) {
        return motherboardService.update(id, motherboard)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete motherboard")
    public ResponseEntity<Void> deleteMotherboard(@PathVariable UUID id) {
        if (motherboardService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}