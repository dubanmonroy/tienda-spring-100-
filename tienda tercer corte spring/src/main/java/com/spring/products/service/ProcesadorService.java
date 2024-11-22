package com.spring.products.service;

import com.spring.products.entity.ProcesadorEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProcesadorService {

    private List<ProcesadorEntity> procesadorList = new ArrayList<>();

    public ProcesadorService() {
        procesadorList.add(new ProcesadorEntity());
    }

    public ProcesadorEntity createProcesador() {
        ProcesadorEntity newProcesador = new ProcesadorEntity();
        procesadorList.add(newProcesador);
        return newProcesador;
    }

    public List<ProcesadorEntity> getAllProcesadores() {
        return procesadorList;
    }

    public Optional<ProcesadorEntity> getProcesadorById(UUID id) {
        return procesadorList.stream().filter(procesador -> procesador.getId().equals(id)).findFirst();
    }

    public Optional<ProcesadorEntity> updateProcesador(UUID id, String name, String category, double price, int stock) {
        Optional<ProcesadorEntity> procesadorOpt = getProcesadorById(id);
        if (procesadorOpt.isPresent()) {
            ProcesadorEntity procesador = procesadorOpt.get();
            procesador.setName(name);
            procesador.setCategory(category);
            procesador.setPrice(price);
            procesador.setStock(stock);
            return Optional.of(procesador);
        }
        return Optional.empty();
    }

    public boolean deleteProcesador(UUID id) {
        return procesadorList.removeIf(procesador -> procesador.getId().equals(id));
    }
}
