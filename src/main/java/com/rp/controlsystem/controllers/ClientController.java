package com.rp.controlsystem.controllers;

import com.rp.controlsystem.dtos.ClientRequest;
import com.rp.controlsystem.dtos.WorkOrderRequest;
import com.rp.controlsystem.models.Client;
import com.rp.controlsystem.models.WorkOrder;
import com.rp.controlsystem.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@ApiResponses (value = {
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado", useReturnTypeSchema = true),
})
@RequestMapping("/clientes")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca todos os clientes cadastrados")
    ResponseEntity<List<Client>> allClients() {
        List<Client> all = clientService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca um cliente cadastrado por ID")
    ResponseEntity<Client> clientById(@PathVariable("id") Integer id) {
        Client client = clientService.findById(id);
        return ResponseEntity.ok().body(client);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastra um cliente")
    ResponseEntity<Void> insert(@RequestBody @Valid Client client) {
        clientService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualiza um cliente por ID")
    ResponseEntity<Client> update(@PathVariable("id") Integer id, @RequestBody @Valid ClientRequest clientRequest) {
        Client client = clientService.update(clientRequest, id);
        return ResponseEntity.ok().body(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleta um cliente por ID")
    ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
