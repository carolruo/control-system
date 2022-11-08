package com.rp.controlsystem.models;

import com.rp.controlsystem.models.enums.Status;

import javax.persistence.*;

@Entity
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "cliente_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
    private Status status;

    protected WorkOrder() {
    }

    public WorkOrder(String description, Client client, Equipment equipment) {
        this.description = description;
        this.client = client;
        this.equipment = equipment;
        this.status = status.PENDING;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
