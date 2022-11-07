package com.rp.controlsystem.models;

import com.rp.controlsystem.models.enums.Status;

import javax.persistence.*;

@Entity
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
    private Status status;

    public WorkOrder() {
    }

    public WorkOrder(String description, Client client, Equipment equipment, Status status) {
        this.description = description;
        this.client = client;
        this.equipment = equipment;
        this.status = status.PENDING;
    }

}
