package com.example.Alianza.controller;

import com.example.Alianza.entity.Client;
import com.example.Alianza.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
@CrossOrigin("*")
public class ClientController {



    @Autowired
    private  ClientService clientService;

    @GetMapping
    public Flux<Client> getAllClientes() {
        return clientService.getAllClients();
    }

    @GetMapping("/{sharedKey}")
    public Mono<Client> getClienteById(@PathVariable String sharedKey) {
        return clientService.getClientById(sharedKey);
    }

    @PostMapping
    public Mono<Client> saveCliente(@RequestBody Client client) {
        return clientService.saveClient(client);
    }


}