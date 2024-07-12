package com.example.Alianza.service;

import com.example.Alianza.entity.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

     Flux<Client> getAllClients();
    Mono<Client> getClientById(String sharedKey);

    Mono<Client> saveClient(Client client);


}
