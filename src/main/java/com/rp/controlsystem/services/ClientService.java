package com.rp.controlsystem.services;

import com.rp.controlsystem.exceptions.DuplicateObjectException;
import com.rp.controlsystem.exceptions.ObjectNotFoundException;
import com.rp.controlsystem.models.Address;
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

    public Client findById(Integer id) {
        return clientRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente com o Id '" + id + "' não existe"));
    }

    public void save(Client client) {
        validateEmail(client);
        validatePhoneNumber(client);
        client.getAddress().setClient(client);
        clientRepository.save(client);
    }

    private void validatePhoneNumber(Client client) {
        if (clientRepository.findByPhoneNumber(client.getPhoneNumber()).isPresent()) {
            throw new DuplicateObjectException("Cliente com esse telefone já cadastrado");
        }
    }

    private void validateEmail(Client client) {
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new DuplicateObjectException("Cliente com esse e-mail já cadastrado");
        }
    }
}
