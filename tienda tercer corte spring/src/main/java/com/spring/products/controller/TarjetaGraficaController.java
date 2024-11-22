package com.spring.products.controller;

import com.spring.products.entity.TarjetaGraficaEntity;
import com.spring.products.service.TarjetaGraficaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tarjetas-graficas")
public class TarjetaGraficaController {

    private final TarjetaGraficaService tarjetaGraficaService;

    public TarjetaGraficaController(TarjetaGraficaService tarjetaGraficaService) {
        this.tarjetaGraficaService = tarjetaGraficaService;
    }

    @PostMapping
    public ResponseEntity<TarjetaGraficaEntity> createTarjetaGrafica() {
        TarjetaGraficaEntity createdTarjetaGrafica = tarjetaGraficaService.createTarjetaGrafica();
        return ResponseEntity.ok(createdTarjetaGrafica);
    }

    @GetMapping
    public List<TarjetaGraficaEntity> getAllTarjetasGraficas() {
        return tarjetaGraficaService.getAllTarjetasGraficas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarjetaGraficaEntity> getTarjetaGraficaById(@PathVariable UUID id) {
        Optional<TarjetaGraficaEntity> tarjeta = tarjetaGraficaService.getTarjetaGraficaById(id);
        return tarjeta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarjetaGraficaEntity> updateTarjetaGrafica(
        @PathVariable UUID id,
        @RequestBody TarjetaGraficaEntity tarjetaDetails
    ) {
        Optional<TarjetaGraficaEntity> updatedTarjeta = tarjetaGraficaService.updateTarjetaGrafica(
            id,
            tarjetaDetails.getName(),
            tarjetaDetails.getCategory(),
            tarjetaDetails.getPrice(),
            tarjetaDetails.getStock()
        );
        return updatedTarjeta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarjetaGrafica(@PathVariable UUID id) {
        boolean deleted = tarjetaGraficaService.deleteTarjetaGrafica(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
