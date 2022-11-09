package com.rp.controlsystem.repositories;

import com.rp.controlsystem.models.OrderReport;
import com.rp.controlsystem.models.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderReportRepository extends JpaRepository<OrderReport, Integer> {

    public List<OrderReport> findByWorkOrder(WorkOrder workOrder);
}
