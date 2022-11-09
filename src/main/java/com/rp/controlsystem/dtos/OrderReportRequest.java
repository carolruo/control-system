package com.rp.controlsystem.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrderReportRequest {

    @NotBlank
    private String message;

    @NotNull
    private Integer workOrderId;

    public OrderReportRequest(String message, Integer workOrderId) {
        this.message = message;
        this.workOrderId = workOrderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Integer workOrderId) {
        this.workOrderId = workOrderId;
    }
}
