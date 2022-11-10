package com.rp.controlsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_report")
public class OrderReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String reportTime;

    @NotBlank
    private String message;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "work_order_id")
    private WorkOrder workOrder;

    protected OrderReport() {
    }

    public OrderReport(String message, WorkOrder workOrder, String reportTime) {
        this.message = message;
        this.workOrder = workOrder;
        this.reportTime = reportTime;
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getReportTime() {
        return reportTime;
    }

    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }
}