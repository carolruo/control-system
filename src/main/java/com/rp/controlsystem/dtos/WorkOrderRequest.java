package com.rp.controlsystem.dtos;

import com.rp.controlsystem.models.Equipment;
import com.rp.controlsystem.models.enums.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WorkOrderRequest {

    @NotBlank
    private String description;

    @NotNull
    private Integer clientId;

    @NotNull
    private Equipment equipment;

    @NotNull
    private Status status;

    public WorkOrderRequest() {
    }

    public WorkOrderRequest(String description, Integer clientId, Equipment equipment, Status status) {
        this.description = description;
        this.clientId = clientId;
        this.equipment = equipment;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }
}
