package com.spring.products.controller;

import com.spring.products.entity.GPUEntity;
import com.spring.products.service.GPUService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/gpus")
@Tag(name = "GPU", description = "GPU management endpoints")
public class GPUController {
    private final GPUService gpuService;

    public GPUController(GPUService gpuService) {
        this.gpuService = gpuService;
    }

    @PostMapping
    @Operation(summary = "Create a new GPU")
    public ResponseEntity<GPUEntity> createGPU(@Valid @RequestBody GPUEntity gpu) {
        return ResponseEntity.ok(gpuService.save(gpu));
    }

    @GetMapping
    @Operation(summary = "Get all GPUs")
    public List<GPUEntity> getAllGPUs() {
        return gpuService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get GPU by ID")
    public ResponseEntity<GPUEntity> getGPUById(@PathVariable UUID id) {
        return gpuService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/manufacturer/{manufacturer}")
    @Operation(summary = "Get GPUs by manufacturer")
    public List<GPUEntity> getGPUsByManufacturer(@PathVariable String manufacturer) {
        return gpuService.findByManufacturer(manufacturer);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update GPU")
    public ResponseEntity<GPUEntity> updateGPU(@PathVariable UUID id, @Valid @RequestBody GPUEntity gpu) {
        return gpuService.update(id, gpu)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete GPU")
    public ResponseEntity<Void> deleteGPU(@PathVariable UUID id) {
        if (gpuService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}