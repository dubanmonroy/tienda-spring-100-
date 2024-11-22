package com.spring.products.controller;

import com.spring.products.entity.MonitorEntity;
import com.spring.products.service.MonitorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/monitors")
public class MonitorController {

    private final MonitorService monitorService;

    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @PostMapping
    public ResponseEntity<MonitorEntity> createMonitor() {
        MonitorEntity createdMonitor = monitorService.createMonitor();
        return ResponseEntity.ok(createdMonitor);
    }

    @GetMapping
    public List<MonitorEntity> getAllMonitors() {
        return monitorService.getAllMonitors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonitorEntity> getMonitorById(@PathVariable UUID id) {
        Optional<MonitorEntity> monitor = monitorService.getMonitorById(id);
        return monitor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonitorEntity> updateMonitor(
        @PathVariable UUID id,
        @RequestBody MonitorEntity monitorDetails
    ) {
        Optional<MonitorEntity> updatedMonitor = monitorService.updateMonitor(
            id,
            monitorDetails.getName(),
            monitorDetails.getCategory(),
            monitorDetails.getPrice(),
            monitorDetails.getStock()
        );
        return updatedMonitor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonitor(@PathVariable UUID id) {
        boolean deleted = monitorService.deleteMonitor(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
