package com.spring.products.controller;

import com.spring.products.entity.CPUEntity;
import com.spring.products.service.CPUService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cpus")
@Tag(name = "CPU", description = "CPU management endpoints")
public class CPUController {
    private final CPUService cpuService;

    public CPUController(CPUService cpuService) {
        this.cpuService = cpuService;
    }

    @PostMapping
    @Operation(summary = "Create a new CPU")
    public ResponseEntity<CPUEntity> createCPU(@Valid @RequestBody CPUEntity cpu) {
        return ResponseEntity.ok(cpuService.save(cpu));
    }

    @GetMapping
    @Operation(summary = "Get all CPUs")
    public List<CPUEntity> getAllCPUs() {
        return cpuService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get CPU by ID")
    public ResponseEntity<CPUEntity> getCPUById(@PathVariable UUID id) {
        return cpuService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/brand/{brand}")
    @Operation(summary = "Get CPUs by brand")
    public List<CPUEntity> getCPUsByBrand(@PathVariable String brand) {
        return cpuService.findByBrand(brand);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update CPU")
    public ResponseEntity<CPUEntity> updateCPU(@PathVariable UUID id, @Valid @RequestBody CPUEntity cpu) {
        return cpuService.update(id, cpu)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete CPU")
    public ResponseEntity<Void> deleteCPU(@PathVariable UUID id) {
        if (cpuService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}