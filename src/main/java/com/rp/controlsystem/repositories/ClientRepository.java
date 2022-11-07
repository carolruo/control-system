package com.rp.controlsystem.repositories;

import com.rp.controlsystem.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
