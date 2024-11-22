package com.spring.products.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {
    public ProductEntity() {
        super();
    }

    public ProductEntity(String name, String category, double price, int stock) {
        super(name, category, price, stock);
    }
}