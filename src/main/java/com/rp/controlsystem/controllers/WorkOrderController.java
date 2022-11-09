package com.rp.controlsystem.controllers;

import com.rp.controlsystem.dtos.OrderReportRequest;
import com.rp.controlsystem.dtos.WorkOrderRequest;
import com.rp.controlsystem.models.OrderReport;
import com.rp.controlsystem.models.WorkOrder;
import com.rp.controlsystem.services.ClientService;
import com.rp.controlsystem.services.OrderReportService;
import com.rp.controlsystem.services.WorkOrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    @Operation(summary = "Busca todas as ordens de serviço cadastradas")
    ResponseEntity<List<WorkOrder>> findAll() {
        List<WorkOrder> all = workOrderService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma ordem de serviço por ID")
    ResponseEntity<WorkOrder> find(@PathVariable("id") Integer id) {
        WorkOrder workOrder = workOrderService.findById(id);
        return ResponseEntity.ok().body(workOrder);
    }

    @PostMapping
    @Operation(summary = "Cadastra uma ordem de serviço")
    ResponseEntity<Void> insert(@RequestBody @Valid WorkOrderRequest workOrderRequest) {
        workOrderService.save(workOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma ordem de serviço por ID")
    ResponseEntity<WorkOrder> update(@PathVariable("id") Integer id, @RequestBody @Valid WorkOrderRequest workOrderRequest) {
        WorkOrder workOrder = workOrderService.update(workOrderRequest, id);
        return ResponseEntity.ok().body(workOrder);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma ordem de serviço por ID")
    ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        workOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pendentes")
    @Operation(summary = "Busca ordens de serviços pendentes")
    ResponseEntity<List<WorkOrder>> findPendingOrder() {
        List<WorkOrder> pendings = workOrderService.findPending();
        return ResponseEntity.ok().body(pendings);
    }

    @GetMapping("/{id}/reports")
    @Operation(summary = "Busca os registros já feitos em uma ordem de serviço por ID")
    ResponseEntity<List<OrderReport>> findOrderReport(@PathVariable("id") Integer id) {
        List<OrderReport> reports = workOrderService.findOrderReport(id);
        return ResponseEntity.ok().body(reports);
    }

    @PostMapping("/{id}/reports")
    @Operation(summary = "Cadastra um registro em uma ordem de serviço por ID")
    ResponseEntity<Void> insertReport(@RequestBody @Valid OrderReportRequest orderReportRequest) {
        orderReportService.save(orderReportRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
