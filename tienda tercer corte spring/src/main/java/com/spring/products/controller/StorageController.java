package com.spring.products.controller;

import com.spring.products.entity.StorageEntity;
import com.spring.products.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/storage")
@Tag(name = "Storage", description = "Storage management endpoints")
public class StorageController {
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping
    @Operation(summary = "Create a new storage device")
    public ResponseEntity<StorageEntity> createStorage(@Valid @RequestBody StorageEntity storage) {
        return ResponseEntity.ok(storageService.save(storage));
    }

    @GetMapping
    @Operation(summary = "Get all storage devices")
    public List<StorageEntity> getAllStorage() {
        return storageService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get storage by ID")
    public ResponseEntity<StorageEntity> getStorageById(@PathVariable UUID id) {
        return storageService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    @Operation(summary = "Get storage by type")
    public List<StorageEntity> getStorageByType(@PathVariable String type) {
        return storageService.findByType(type);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update storage")
    public ResponseEntity<StorageEntity> updateStorage(@PathVariable UUID id, @Valid @RequestBody StorageEntity storage) {
        return storageService.update(id, storage)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete storage")
    public ResponseEntity<Void> deleteStorage(@PathVariable UUID id) {
        if (storageService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}