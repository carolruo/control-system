package com.rp.controlsystem;

import com.rp.controlsystem.models.Address;
import com.rp.controlsystem.models.Client;
import com.rp.controlsystem.models.Equipment;
import com.rp.controlsystem.models.WorkOrder;
import com.rp.controlsystem.repositories.AddressRepository;
import com.rp.controlsystem.repositories.ClientRepository;
import com.rp.controlsystem.repositories.EquipmentRepository;
import com.rp.controlsystem.repositories.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ControlSystemApplication implements CommandLineRunner {

	@Autowired
	AddressRepository addressRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	EquipmentRepository equipmentRepository;
	@Autowired
	WorkOrderRepository workOrderRepository;

	public static void main(String[] args) {
		SpringApplication.run(ControlSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Address address1 = new Address("Rua das Rosas", 10, "Sao paulo", "SP", "17524-213");
		Address address2 = new Address("Rua das Camelias", 10, "Pelotas", "RS", "54524-298");
		addressRepository.saveAll(Arrays.asList(address1, address2));

		Client c1 = new Client("Jasmim", "1498273456", "jasmim@gmail", address1);
		Client c2 = new Client("Joao", "4198273452", "joao@gmail", address2);
		address1.setClient(c1);
		address2.setClient(c2);
		clientRepository.saveAll(Arrays.asList(c1, c2));

//		Equipment e1 = new Equipment("TV Samsung VG234", "eletronico", "Samsung");
//		Equipment e2 = new Equipment("Smartphone Samsung 12", "celular", "Samsung");
//		equipmentRepository.saveAll(Arrays.asList(e1, e2));
//
//		WorkOrder w1 = new WorkOrder("Transistor queimado", c1, e2);
//		WorkOrder w2 = new WorkOrder("Tela quebrada", c2, e1);
//		workOrderRepository.saveAll(Arrays.asList(w1, w2));
//		c1.addOrder(w1);
//		c2.addOrder(w2);
	}
}
