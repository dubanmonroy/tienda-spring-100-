package com.spring.products.controller;

import com.spring.products.entity.ProcesadorEntity;
import com.spring.products.service.ProcesadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/procesadores")
public class ProcesadorController {

    private final ProcesadorService procesadorService;

    public ProcesadorController(ProcesadorService procesadorService) {
        this.procesadorService = procesadorService;
    }

    @PostMapping
    public ResponseEntity<ProcesadorEntity> createProcesador() {
        ProcesadorEntity createdProcesador = procesadorService.createProcesador();
        return ResponseEntity.ok(createdProcesador);
    }

    @GetMapping
    public List<ProcesadorEntity> getAllProcesadores() {
        return procesadorService.getAllProcesadores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcesadorEntity> getProcesadorById(@PathVariable UUIDBaseEntity.java
