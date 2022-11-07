package com.rp.controlsystem.controllers;

import com.rp.controlsystem.models.Client;
import com.rp.controlsystem.models.WorkOrder;
import com.rp.controlsystem.services.WorkOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/ordens")
public class WorkOrderController {

    private final WorkOrderService workOrderService;


    public WorkOrderController(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
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
    ResponseEntity<Void> insert(@RequestBody  WorkOrder workOrder) {
        workOrderService.save(workOrder);
        URI location = URI.create(format("/courses/%s", workOrder.getId()));
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<WorkOrder> update(@PathVariable("id") Integer id, @RequestBody WorkOrder workOrder) {
        workOrderService.update(workOrder, id);
        return ResponseEntity.ok().body(workOrder);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        workOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
