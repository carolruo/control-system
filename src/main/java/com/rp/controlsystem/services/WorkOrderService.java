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
                .orElseThrow(() -> new ObjectNotFoundException("Ordem de Serviço de id " + id + " inexistente"));
    }

    public void save(WorkOrder workOrder) {
        workOrderRepository.save(workOrder);
    }

    public void update(Integer id) {

    }
}
