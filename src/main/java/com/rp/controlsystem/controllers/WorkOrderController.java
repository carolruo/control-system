package com.rp.controlsystem.controllers;

import com.rp.controlsystem.models.Client;
import com.rp.controlsystem.models.WorkOrder;
import com.rp.controlsystem.services.WorkOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkOrderController {

    private final WorkOrderService workOrderService;


    public WorkOrderController(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }

    @GetMapping("/ordens")
    ResponseEntity<List<WorkOrder>> allClients() {
        List<WorkOrder> all = workOrderService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/ordens/{id}")
    ResponseEntity<WorkOrder> clientByEmail(@PathVariable("id") String id) {
        Integer intId = Integer.parseInt(id);
        WorkOrder workOrder = workOrderService.findById(intId);
        return ResponseEntity.ok().body(workOrder);
    }
}
