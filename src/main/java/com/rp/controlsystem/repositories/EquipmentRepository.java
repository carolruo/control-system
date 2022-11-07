package com.rp.controlsystem.repositories;

import com.rp.controlsystem.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
}
