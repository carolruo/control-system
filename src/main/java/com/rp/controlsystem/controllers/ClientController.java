package com.rp.controlsystem.controllers;

import com.rp.controlsystem.models.Client;
import com.rp.controlsystem.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @Operation(summary = "Busca todos os clientes cadastrados")
    ResponseEntity<List<Client>> allClients() {
        List<Client> all = clientService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um cliente cadastrado por ID")
    ResponseEntity<Client> clientById(@PathVariable("id") Integer id) {
        Client client = clientService.findById(id);
        return ResponseEntity.ok().body(client);
    }

    @PostMapping
    @Operation(summary = "Cadastra um cliente")
    ResponseEntity<Void> insert(@RequestBody @Valid Client client) {
        clientService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
