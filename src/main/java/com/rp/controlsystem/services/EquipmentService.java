package com.rp.controlsystem.services;

import com.rp.controlsystem.exceptions.ObjectNotFoundException;
import com.rp.controlsystem.models.Equipment;
import com.rp.controlsystem.repositories.EquipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment findById(Integer id) {
        return equipmentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Equipamento com Id " + id + " não foi encontrado"));
    }

    public Equipment findByModelAndBrand(String model, String brand) {
        return equipmentRepository.findByModelAndBrand(model, brand).orElseThrow(() -> new ObjectNotFoundException("Equipamento não cadastrado. Modelo: " + model + " Marca: " + brand));
    }
}
