package com.rp.controlsystem.services;

import com.rp.controlsystem.models.Client;
import com.rp.controlsystem.repositories.ClientRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException("obj nao encontrado", "client"));
    }

    public void save(Client client) {
        clientRepository.save(client);
    }
}
