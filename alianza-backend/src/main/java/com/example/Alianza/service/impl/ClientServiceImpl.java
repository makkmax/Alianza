package com.example.Alianza.service.impl;

import com.example.Alianza.entity.Client;
import com.example.Alianza.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
    private final List<Client> clients = new ArrayList<>();

    public ClientServiceImpl() {
        clients.add(new Client( "key1", "John Doe", "123456789", "john@example.com", "2023-01-01", "2023-12-31"));
        clients.add(new Client( "key2", "Jane Smith", "987654321", "jane@example.com", "2023-02-01", "2023-11-30"));
    }

    @Override
    public Flux<Client> getAllClients() {
        try {
            logger.info("Getting all clients");
            return Flux.fromIterable(clients);
        }catch (Exception e) {
             logger.error("Error getting all clients: {}", e.getMessage());
             return Flux.error(e);
        }
    }

    @Override
    public Mono<Client> getClientById(String sharedKey) {
        try {
            logger.info("Getting client by shared key: {}", sharedKey);
            Optional<Client> clientOpt = clients.stream()
                    .filter(c -> c.getSharedKey().equals(sharedKey))
                    .findFirst();
            return clientOpt.map(Mono::just).orElseGet(Mono::empty);
        } catch (Exception e) {
            logger.error("Error getting client by shared key: {}", e.getMessage());
            return Mono.error(e);
        }
    }

    @Override
    public Mono<Client> saveClient(Client client) {
        try {
            logger.info("Saving client: {}", client);
            clients.add(client);
            return Mono.just(client);
        }catch (Exception e) {
             logger.error("Error saving client: {}", e.getMessage());
             return Mono.error(e);
        }
    }

}
