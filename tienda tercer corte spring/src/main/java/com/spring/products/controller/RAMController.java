package com.spring.products.controller;

import com.spring.products.entity.RAMEntity;
import com.spring.products.service.RAMService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rams")
@Tag(name = "RAM", description = "RAM management endpoints")
public class RAMController {
    private final RAMService ramService;

    public RAMController(RAMService ramService) {
        this.ramService = ramService;
    }

    @PostMapping
    @Operation(summary = "Create a new RAM")
    public ResponseEntity<RAMEntity> createRAM(@Valid @RequestBody RAMEntity ram) {
        return ResponseEntity.ok(ramService.save(ram));
    }

    @GetMapping
    @Operation(summary = "Get all RAM modules")
    public List<RAMEntity> getAllRAMs() {
        return ramService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get RAM by ID")
    public ResponseEntity<RAMEntity> getRAMById(@PathVariable UUID id) {
        return ramService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/capacity/{capacity}")
    @Operation(summary = "Get RAM by capacity")
    public List<RAMEntity> getRAMsByCapacity(@PathVariable int capacity) {
        return ramService.findByCapacity(capacity);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update RAM")
    public ResponseEntity<RAMEntity> updateRAM(@PathVariable UUID id, @Valid @RequestBody RAMEntity ram) {
        return ramService.update(id, ram)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete RAM")
    public ResponseEntity<Void> deleteRAM(@PathVariable UUID id) {
        if (ramService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}