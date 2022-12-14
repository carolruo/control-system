package com.rp.controlsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rp.controlsystem.models.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String description;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cliente_id")
    private Client client;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @NotNull
    private Status status;

    @JsonInclude(Include.NON_NULL)
    private String startTime;

    @JsonInclude(Include.NON_NULL)
    private String finishTime;

    @OneToMany(mappedBy = "workOrder")
    private List<OrderReport> reports = new java.util.ArrayList<>();

    public List<OrderReport> getReports() {
        return reports;
    }

    public void setReports(List<OrderReport> reports) {
        this.reports = reports;
    }

    protected WorkOrder() {
    }

    public WorkOrder(String description, Client client, Equipment equipment) {
        this.description = description;
        this.client = client;
        this.equipment = equipment;
        this.status = status.PENDING;
    }

    public WorkOrder(String description, Client client, Equipment equipment, Status status) {
        this.description = description;
        this.client = client;
        this.equipment = equipment;
        this.status = status;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
