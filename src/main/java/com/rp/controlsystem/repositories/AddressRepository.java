package com.rp.controlsystem.repositories;

import com.rp.controlsystem.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
