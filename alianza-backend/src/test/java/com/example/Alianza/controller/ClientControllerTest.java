package com.example.Alianza.controller;

import com.example.Alianza.entity.Client;
import com.example.Alianza.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @Captor
    private ArgumentCaptor<Client> clientArgumentCaptor;

    private ClientController clientController;

    @BeforeEach
    public void setUp() {
        clientController = new ClientController(clientService);
    }

    @Test
    public void getAllClientes_whenNoClientsExist_returnsEmptyList() {
        // given
        given(clientService.getAllClients()).willReturn(Flux.empty());

        // when
        Flux<Client> result = clientController.getAllClientes();

        // then
        assertNotNull(result);
        assertEquals(0, result.collectList().block().size());
    }


    @Test
    public void testGetClienteById() {
        // given
        String sharedKey = "key3";
        Client client = new Client("key3", "New Client", "1234567890", "new@example.com", "2023-03-01", "2023-12-31");
        when(clientService.getClientById(sharedKey)).thenReturn(Mono.just(client));


        Client result = clientController.getClienteById(sharedKey).block();

        // then
        verify(clientService).getClientById(sharedKey);
        assertEquals(client, result);
    }


    @Test
    public void testSaveCliente() {
        // given
        Client client = new Client("key3", "New Client", "1234567890", "new@example.com", "2023-03-01", "2023-12-31");
        when(clientService.saveClient(any(Client.class))).thenReturn(Mono.just(client));

        // when
        Client result = clientController.saveCliente(client).block();

        // then
        verify(clientService).saveClient(clientArgumentCaptor.capture());
        assertEquals(client, clientArgumentCaptor.getValue());
        assertEquals(client, result);
    }


}