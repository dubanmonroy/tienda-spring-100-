package com.spring.products.service;

import com.spring.products.entity.MonitorEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MonitorService {

    private List<MonitorEntity> monitorList = new ArrayList<>();

    public MonitorService() {
        monitorList.add(new MonitorEntity());  // Carga un monitor con datos predeterminados
    }

    public MonitorEntity createMonitor() {
        MonitorEntity newMonitor = new MonitorEntity();
        monitorList.add(newMonitor);
        return newMonitor;
    }

    public List<MonitorEntity> getAllMonitors() {
        return monitorList;
    }

    public Optional<MonitorEntity> getMonitorById(UUID id) {
        return monitorList.stream().filter(monitor -> monitor.getId().equals(id)).findFirst();
    }

    public Optional<MonitorEntity> updateMonitor(UUID id, String name, String category, double price, int stock) {
        Optional<MonitorEntity> monitorOpt = getMonitorById(id);
        if (monitorOpt.isPresent()) {
            MonitorEntity monitor = monitorOpt.get();
            monitor.setName(name);
            monitor.setCategory(category);
            monitor.setPrice(price);
            monitor.setStock(stock);
            return Optional.of(monitor);
        }
        return Optional.empty();
    }

    public boolean deleteMonitor(UUID id) {
        return monitorList.removeIf(monitor -> monitor.getId().equals(id));
    }
}
