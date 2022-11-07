package com.rp.controlsystem.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String model;
    private String type;
    private String brand;

    protected Equipment() {
    }

    public Equipment(String model, String type, String brand) {
        this.model = model;
        this.type = type;
        this.brand = brand;
    }

}
