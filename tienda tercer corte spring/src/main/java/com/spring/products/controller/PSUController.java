package com.spring.products.controller;

import com.spring.products.entity.PSUEntity;
import com.spring.products.service.PSUService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/psus")
@Tag(name = "PSU", description = "Power Supply Unit management endpoints")
public class PSUController {
    private final PSUService psuService;

    public PSUController(PSUService psuService) {
        this.psuService = psuService;
    }

    @PostMapping
    @Operation(summary = "Create a new PSU")
    public ResponseEntity<PSUEntity> createPSU(@Valid @RequestBody PSUEntity psu) {
        return ResponseEntity.ok(psuService.save(psu));
    }

    @GetMapping
    @Operation(summary = "Get all PSUs")
    public List<PSUEntity> getAllPSUs() {
        return psuService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get PSU by ID")
    public ResponseEntity<PSUEntity> getPSUById(@PathVariable UUID id) {
        return psuService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/wattage/{minWattage}")
    @Operation(summary = "Get PSUs by minimum wattage")
    public List<PSUEntity> getPSUsByMinWattage(@PathVariable int minWattage) {
        return psuService.findByMinWattage(minWattage);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update PSU")
    public ResponseEntity<PSUEntity> updatePSU(@PathVariable UUID id, @Valid @RequestBody PSUEntity psu) {
        return psuService.update(id, psu)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete PSU")
    public ResponseEntity<Void> deletePSU(@PathVariable UUID id) {
        if (psuService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}