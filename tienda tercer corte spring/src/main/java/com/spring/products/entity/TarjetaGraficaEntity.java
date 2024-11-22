package com.spring.products.entity;

import java.util.UUID;

public class TarjetaGraficaEntity {

    private UUID id;
    private String name;
    private String category;
    private double price;
    private int stock;

    public TarjetaGraficaEntity() {
        this.id = UUID.randomUUID();
        this.name = "Tarjeta Grafica";
        this.category = "Rendimiento";
        this.price = 900.000;
        this.stock = 80;
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
