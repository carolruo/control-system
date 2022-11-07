package com.rp.controlsystem.repositories;

import com.rp.controlsystem.models.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Integer> {
}
