package com.example.Alianza.service;

import com.example.Alianza.entity.Client;
import com.example.Alianza.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        clientService = new ClientServiceImpl();
    }

    @Test
    public void testGetAllClients() {
        Flux<Client> clients = clientService.getAllClients();

        StepVerifier.create(clients)
                .expectNextMatches(client -> client.getSharedKey().equals("key1"))
                .expectNextMatches(client -> client.getSharedKey().equals("key2"))
                .verifyComplete();
    }

    @Test
    public void testGetClientById() {
        Mono<Client> clientMono = clientService.getClientById("key1");

        StepVerifier.create(clientMono)
                .expectNextMatches(client -> client.getSharedKey().equals("key1"))
                .verifyComplete();
    }

    @Test
    public void testGetClientByIdNotFound() {
        Mono<Client> clientMono = clientService.getClientById("key3");

        StepVerifier.create(clientMono)
                .verifyComplete();
    }

    @Test
    public void testSaveClient() {
        Client newClient = new Client("key3", "New Client", "1234567890", "new@example.com", "2023-03-01", "2023-12-31");
        Mono<Client> savedClientMono = clientService.saveClient(newClient);

        StepVerifier.create(savedClientMono)
                .expectNextMatches(client -> client.getSharedKey().equals("key3"))
                .verifyComplete();

        // Verify that the client was added to the list
        assertNotNull(clientService.getClientById("key3").block());
    }

    @Test
    public void testSaveClientNull() {
        Mono<Client> savedClientMono = clientService.saveClient(null);

        StepVerifier.create(savedClientMono)
                .expectError(NullPointerException.class)
                .verify();
    }
}
