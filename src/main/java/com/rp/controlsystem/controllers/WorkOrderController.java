package com.rp.controlsystem.controllers;

import com.rp.controlsystem.dtos.OrderReportRequest;
import com.rp.controlsystem.dtos.WorkOrderRequest;
import com.rp.controlsystem.models.OrderReport;
import com.rp.controlsystem.models.WorkOrder;
import com.rp.controlsystem.services.ClientService;
import com.rp.controlsystem.services.OrderReportService;
import com.rp.controlsystem.services.WorkOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/ordens")
public class WorkOrderController {

    private final WorkOrderService workOrderService;
    private final ClientService clientService;
    private final OrderReportService orderReportService;


    public WorkOrderController(WorkOrderService workOrderService, ClientService clientService, OrderReportService orderReportService) {
        this.workOrderService = workOrderService;
        this.clientService = clientService;
        this.orderReportService = orderReportService;
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
    ResponseEntity<Void> insert(@RequestBody @Valid WorkOrderRequest workOrderRequest) {
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

    @GetMapping("/pendentes")
    ResponseEntity<List<WorkOrder>> findPendingOrder() {
        List<WorkOrder> pendings = workOrderService.findPending();
        return ResponseEntity.ok().body(pendings);
    }

    @GetMapping("/{id}/reports")
    ResponseEntity<List<OrderReport>> findOrderReport(@PathVariable("id") Integer id) {
        List<OrderReport> reports = workOrderService.findOrderReport(id);
        return ResponseEntity.ok().body(reports);
    }

    @PostMapping("/{id}/reports")
    ResponseEntity<Void> insertReport(@RequestBody @Valid OrderReportRequest orderReportRequest) {
        orderReportService.save(orderReportRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
