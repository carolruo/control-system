package com.rp.controlsystem.controllers;

import com.rp.controlsystem.models.Client;
import com.rp.controlsystem.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    ResponseEntity<List<Client>> allClients() {
        List<Client> all = clientService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    ResponseEntity<Client> clientById(@PathVariable("id") Integer id) {
        Client client = clientService.findById(id);
        return ResponseEntity.ok().body(client);
    }

    @PostMapping
    ResponseEntity<Void> insert(@RequestBody Client client) {
        clientService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
