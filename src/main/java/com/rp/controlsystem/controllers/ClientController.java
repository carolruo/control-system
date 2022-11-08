package com.rp.controlsystem.controllers;

import com.rp.controlsystem.models.Client;
import com.rp.controlsystem.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clientes")
    ResponseEntity<List<Client>> allClients() {
        List<Client> all = clientService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/clientes/{id}")
    ResponseEntity<Client> clientById(@PathVariable("id") Integer id) {
        Client client = clientService.findById(id);
        return ResponseEntity.ok().body(client);
    }


}
