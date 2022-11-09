package com.rp.controlsystem.controllers;

import com.rp.controlsystem.dtos.WorkOrderRequest;
import com.rp.controlsystem.models.Client;
import com.rp.controlsystem.models.WorkOrder;
import com.rp.controlsystem.services.ClientService;
import com.rp.controlsystem.services.WorkOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/ordens")
public class WorkOrderController {

    private final WorkOrderService workOrderService;
    private final ClientService clientService;


    public WorkOrderController(WorkOrderService workOrderService, ClientService clientService) {
        this.workOrderService = workOrderService;
        this.clientService = clientService;
    }

    @GetMapping
    ResponseEntity<List<WorkOrder>> findAll() {
        List<WorkOrder> all = workOrderService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    ResponseEntity<WorkOrder> find(@PathVariable("id") Integer id) {
        WorkOrder workOrder = workOrderService.findById(id);
        return ResponseEntity.ok().body(workOrder);
    }

    @PostMapping
    ResponseEntity<Void> insert(@RequestBody WorkOrderRequest workOrderRequest) {
        workOrderService.save(workOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<WorkOrder> update(@PathVariable("id") Integer id, @RequestBody @Valid WorkOrderRequest workOrderRequest) {
        WorkOrder workOrder = workOrderService.update(workOrderRequest, id);
        return ResponseEntity.ok().body(workOrder);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        workOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
