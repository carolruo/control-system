package com.rp.controlsystem.dtos;

import com.rp.controlsystem.models.Equipment;

public class WorkOrderRequest {

    private String description;

    private Integer clientId;

    private Equipment equipment;

    public WorkOrderRequest(String description, Integer clientId, Equipment equipment) {
        this.description = description;
        this.clientId = clientId;
        this.equipment = equipment;
    }

    public Integer getClientId() {
        return clientId;
    }

    public String getDescription() {
        return description;
    }

    public Equipment getEquipment() {
        return equipment;
    }
}
