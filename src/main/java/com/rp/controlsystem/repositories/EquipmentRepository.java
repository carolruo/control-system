package com.rp.controlsystem.repositories;

import com.rp.controlsystem.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    public Optional<Equipment> findByModelAndBrand(String model, String brand);
}
