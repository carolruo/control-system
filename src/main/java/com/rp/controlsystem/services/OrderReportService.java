package com.rp.controlsystem.services;

import com.rp.controlsystem.dtos.OrderReportRequest;
import com.rp.controlsystem.models.OrderReport;
import com.rp.controlsystem.models.WorkOrder;
import com.rp.controlsystem.repositories.OrderReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderReportService {

    private final OrderReportRepository orderReportRepository;
    private final WorkOrderService workOrderService;


    public OrderReportService(OrderReportRepository orderReportRepository, WorkOrderService workOrderService) {
        this.orderReportRepository = orderReportRepository;
        this.workOrderService = workOrderService;
    }

    public void save(OrderReportRequest orderReportRequest) {
        WorkOrder workOrder = workOrderService.findById(orderReportRequest.getWorkOrderId());
        OrderReport orderReport = new OrderReport(orderReportRequest.getMessage(), workOrder);
        orderReportRepository.save(orderReport);
    }

    public List<OrderReport> findByWorkOrder(WorkOrder workOrder) {
        return orderReportRepository.findByWorkOrder(workOrder);
    }
}
