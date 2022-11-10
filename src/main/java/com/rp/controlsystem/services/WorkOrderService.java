package com.rp.controlsystem.services;

import com.rp.controlsystem.dtos.WorkOrderRequest;
import com.rp.controlsystem.exceptions.ObjectNotFoundException;
import com.rp.controlsystem.models.Client;
import com.rp.controlsystem.models.Equipment;
import com.rp.controlsystem.models.OrderReport;
import com.rp.controlsystem.models.WorkOrder;
import com.rp.controlsystem.models.enums.Status;
import com.rp.controlsystem.repositories.WorkOrderRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkOrderService {

    private final WorkOrderRepository workOrderRepository;
    private final ClientService clientService;
    private final EquipmentService equipmentService;
    private final OrderReportService orderReportService;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    public WorkOrderService(WorkOrderRepository workOrderRepository, ClientService clientService, EquipmentService equipmentService, @Lazy OrderReportService orderReportService) {
        this.workOrderRepository = workOrderRepository;
        this.clientService = clientService;
        this.equipmentService = equipmentService;
        this.orderReportService = orderReportService;
    }

    public List<WorkOrder> findAll() {
        return workOrderRepository.findAll();
    }

    public WorkOrder findById(Integer id) {

        return workOrderRepository
                .findAll()
                .stream()
                .filter(workOrder -> workOrder.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Ordem de Serviço de id " + id + " não encontrada"));
    }

    public void save(WorkOrderRequest workOrderRequest) {
        Client client = clientService.findById(workOrderRequest.getClientId());
        Equipment equipment = getEquipment(workOrderRequest);
        if (equipmentService.verifyDuplicity(equipment)) {
            equipment = equipmentService.findByModelAndBrand(equipment.getModel(), equipment.getBrand());
        }

        equipmentService.save(equipment);
        WorkOrder workOrder = new WorkOrder(workOrderRequest.getDescription(), client, equipment);
        workOrder.setStatus(Status.PENDING);
        workOrderRepository.save(workOrder);
    }

    private Equipment getEquipment(WorkOrderRequest workOrderRequest) {
        Equipment equipment = workOrderRequest.getEquipment();
        equipment.setBrand(workOrderRequest.getEquipment().getBrand());
        equipment.setModel(workOrderRequest.getEquipment().getModel());
        equipment.setType(workOrderRequest.getEquipment().getType());
        return equipment;
    }

    public WorkOrder update(WorkOrderRequest newWorkOrder, Integer id) {
        WorkOrder workOrder = findById(id);

        workOrder.setDescription(newWorkOrder.getDescription());
        workOrder.setClient(clientService.findById(newWorkOrder.getClientId()));

        if (equipmentService.verifyDuplicity(newWorkOrder.getEquipment())) {
            workOrder.setEquipment(equipmentService.findByModelAndBrand(newWorkOrder.getEquipment().getModel(), newWorkOrder.getEquipment().getBrand()));
        } else {
            workOrder.setEquipment(newWorkOrder.getEquipment());
            equipmentService.save(newWorkOrder.getEquipment());
        }

        workOrder.setStatus(newWorkOrder.getStatus());
        setOrderStartTime(newWorkOrder, workOrder);
        setOrderFinishTime(newWorkOrder, workOrder);

        workOrderRepository.save(workOrder);
        return workOrder;
    }

    private void setOrderFinishTime(WorkOrderRequest newWorkOrder, WorkOrder workOrder) {
        if (newWorkOrder.getStatus().equals(Status.DONE)) {
            workOrder.setFinishTime(LocalDateTime.now().format(formatter));
        }
    }

    private void setOrderStartTime(WorkOrderRequest newWorkOrder, WorkOrder workOrder) {
        if (newWorkOrder.getStatus().equals(Status.ACTIVE)) {
            workOrder.setStartTime(LocalDateTime.now().format(formatter));
        }
    }

    public void delete(Integer id) {
        WorkOrder workOrder = findById(id);
        workOrderRepository.delete(workOrder);
    }

    public List<WorkOrder> findPending() {
        return findAll().stream().filter(s -> s.getStatus().equals(Status.PENDING)).collect(Collectors.toList());
    }

    public List<OrderReport> findOrderReport(Integer id) {
        WorkOrder workOrder = findById(id);
        List<OrderReport> reports = orderReportService.findByWorkOrder(workOrder);
        return reports;
    }
}
