package com.spring.products.entity;

public class MonitorEntity extends BaseEntity {
    public MonitorEntity() {
        super();
        this.name = "Monitor";
        this.category = "Visual";
        this.price = 700.000;
        this.stock = 50;
    }
}