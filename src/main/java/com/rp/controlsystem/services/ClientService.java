package com.rp.controlsystem.services;

import com.rp.controlsystem.exceptions.ObjectNotFoundException;
import com.rp.controlsystem.models.Client;
import com.rp.controlsystem.repositories.ClientRepository;
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
        return clientRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException("Cliente com o e-mail '" + email + "' não existe"));
    }

    public void save(Client client) {
        clientRepository.save(client);
    }
}
