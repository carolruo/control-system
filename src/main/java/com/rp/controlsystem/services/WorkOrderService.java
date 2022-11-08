package com.rp.controlsystem.services;

import com.rp.controlsystem.dtos.WorkOrderRequest;
import com.rp.controlsystem.exceptions.ObjectNotFoundException;
import com.rp.controlsystem.models.Client;
import com.rp.controlsystem.models.Equipment;
import com.rp.controlsystem.models.WorkOrder;
import com.rp.controlsystem.repositories.WorkOrderRepository;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class WorkOrderService {

    private final WorkOrderRepository workOrderRepository;
    private final ClientService clientService;
    private final EquipmentService equipmentService;


    public WorkOrderService(WorkOrderRepository workOrderRepository, ClientService clientService, EquipmentService equipmentService) {
        this.workOrderRepository = workOrderRepository;
        this.clientService = clientService;
        this.equipmentService = equipmentService;
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
        Equipment equipment = workOrderRequest.getEquipment();
        WorkOrder workOrder = new WorkOrder(workOrderRequest.getDescription(), client, equipment);
        equipment.setBrand(workOrderRequest.getEquipment().getBrand());
        equipment.setModel(workOrderRequest.getEquipment().getModel());
        equipment.setType(workOrderRequest.getEquipment().getType());
        workOrderRepository.save(workOrder);
    }

    public WorkOrder update(WorkOrderRequest newWorkOrder, Integer id) {
        WorkOrder workOrder = findById(id);

        workOrder.setDescription(newWorkOrder.getDescription());
        workOrder.setClient(clientService.findById(newWorkOrder.getClientId()));

        workOrder.setEquipment(equipmentService.findByModelAndBrand(newWorkOrder.getEquipment().getModel(), newWorkOrder.getEquipment().getBrand()));
        workOrder.setStatus(newWorkOrder.getStatus());
        workOrderRepository.save(workOrder);
        return workOrder;
    }

    public void delete(Integer id) {
        WorkOrder workOrder = findById(id);
        workOrderRepository.delete(workOrder);
    }
}
