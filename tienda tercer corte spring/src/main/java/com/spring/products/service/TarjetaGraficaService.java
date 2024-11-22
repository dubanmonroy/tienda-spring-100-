package com.spring.products.service;

import com.spring.products.entity.TarjetaGraficaEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TarjetaGraficaService {

    private List<TarjetaGraficaEntity> tarjetaGraficaList = new ArrayList<>();

    public TarjetaGraficaService() {
        tarjetaGraficaList.add(new TarjetaGraficaEntity());
    }

    public TarjetaGraficaEntity createTarjetaGrafica() {
        TarjetaGraficaEntity newTarjetaGrafica = new TarjetaGraficaEntity();
        tarjetaGraficaList.add(newTarjetaGrafica);
        return newTarjetaGrafica;
    }

    public List<TarjetaGraficaEntity> getAllTarjetasGraficas() {
        return tarjetaGraficaList;
    }

    public Optional<TarjetaGraficaEntity> getTarjetaGraficaById(UUID id) {
        return tarjetaGraficaList.stream().filter(tarjeta -> tarjeta.getId().equals(id)).findFirst();
    }

    public Optional<TarjetaGraficaEntity> updateTarjetaGrafica(UUID id, String name, String category, double price, int stock) {
        Optional<TarjetaGraficaEntity> tarjetaOpt = getTarjetaGraficaById(id);
        if (tarjetaOpt.isPresent()) {
            TarjetaGraficaEntity tarjeta = tarjetaOpt.get();
            tarjeta.setName(name);
            tarjeta.setCategory(category);
            tarjeta.setPrice(price);
            tarjeta.setStock(stock);
            return Optional.of(tarjeta);
        }
        return Optional.empty();
    }

    public boolean deleteTarjetaGrafica(UUID id) {
        return tarjetaGraficaList.removeIf(tarjeta -> tarjeta.getId().equals(id));
    }
}
