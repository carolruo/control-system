package com.rp.controlsystem.services;

import com.rp.controlsystem.exceptions.ObjectNotFoundException;
import com.rp.controlsystem.models.WorkOrder;
import com.rp.controlsystem.repositories.WorkOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkOrderService {

    private final WorkOrderRepository workOrderRepository;


    public WorkOrderService(WorkOrderRepository workOrderRepository) {
        this.workOrderRepository = workOrderRepository;
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

    public void save(WorkOrder workOrder) {
            workOrderRepository.save(workOrder);
    }

    public WorkOrder update(WorkOrder newWorkOrder, Integer id) {
        WorkOrder workOrder = findById(id);

        workOrder.setClient(newWorkOrder.getClient());
        workOrder.setDescription(newWorkOrder.getDescription());
        workOrder.setEquipment(newWorkOrder.getEquipment());
        workOrder.setStatus(newWorkOrder.getStatus());
        workOrderRepository.save(workOrder);
        return workOrder;
    }

    public void delete(Integer id) {
        WorkOrder workOrder = findById(id);

        workOrderRepository.delete(workOrder);
    }
}
