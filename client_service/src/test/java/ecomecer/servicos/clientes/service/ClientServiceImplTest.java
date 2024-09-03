package ecomecer.servicos.clientes.service;

import ecomecer.servicos.clientes.domain.exception.ClientNotFoundException;
import ecomecer.servicos.clientes.domain.model.Address;
import ecomecer.servicos.clientes.domain.model.Client;
import ecomecer.servicos.clientes.domain.repository.ClientRepository;
import ecomecer.servicos.clientes.domain.service.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl service;

    @Mock
    private ClientRepository repository;

    private Client client = new Client();
    private Address address = new Address();

    @BeforeEach
    void setUp(){
        address.setCity("City");
        address.setNumber(32);
        address.setStreet("streetTest");
        address.setPostal_number(53535353);
        client.setName("ClientTest");
        client.setEmail("clientTest@mail.com");
        client.setBirth_day(LocalDate.of(1997,05,22));
        client.setAddress(address);
    }

    @Nested
    class addClient{

        @Test
        void given_Client_When_Add_Return_Client(){
            client.setId(1L);
            given(repository.save(client)).willReturn(client);

            var newClient = service.addClient(client);

            assertTrue(newClient.getId() != null & newClient.getId() > 0);
            assertEquals(client.getName(), newClient.getName());
            assertEquals(client.getEmail(), newClient.getEmail());
            assertEquals(client.getBirth_day(), newClient.getBirth_day());
            assertEquals(client.getAddress(), newClient.getAddress());
        }
    }

    @Nested
    class getClient {

        @Test
        void given_clientId_When_GetOneById_ReturnClient(){
            client.setId(1L);
            given(repository.findById(anyLong())).willReturn(Optional.of(client));

            var clientInDb = service.getClient(anyLong());

            assertTrue(clientInDb.getId() != null & clientInDb.getId() > 0);
            assertEquals(client.getName(), clientInDb.getName());
            assertEquals(client.getEmail(), clientInDb.getEmail());
            assertEquals(client.getBirth_day(), clientInDb.getBirth_day());
            assertEquals(client.getAddress(), clientInDb.getAddress());

        }

        @Test
        void given_WrongId_Then_Throw_ClientNotFoundException(){
            given(repository.findById(anyLong())).willReturn(Optional.empty());

            var result = assertThrows(ClientNotFoundException.class,
                    () ->{service.getClient(1L);
            });

            assertEquals(result.getMessage(), "Client with Id: 1 not found");

        }
    }

    @Nested
    class getAllClients {

        @Test
        void when_GetAllClients_Then_Return_PageOfClients(){
            Pageable pageable = PageRequest.of(0,5);
            Client client2 = new Client();
            Address address2 = new Address();
            address2.setCity("City2");
            address2.setNumber(2);
            address2.setStreet("streetTest2");
            address2.setPostal_number(532253);
            client2.setName("ClientTest2");
            client2.setEmail("clientTest2@mail.com");
            client2.setBirth_day(LocalDate.of(2000,02,2));
            client2.setAddress(address2);

            var mockListOfClients = List.of(client, client2);
            var mockPageOfClients = new PageImpl<>(mockListOfClients,pageable, mockListOfClients.size());

            given(repository.findAll(pageable)).willReturn(mockPageOfClients);

            var pageOfClients = service.getAllClients(pageable);

            assertNotNull(pageOfClients);
            assertTrue(pageOfClients instanceof Page<Client>);
            assertEquals(pageOfClients.getContent().size(), 2);
            assertEquals(pageOfClients.getContent().get(0).getName(),client.getName());
            assertEquals(pageOfClients.getContent().get(1).getName(),client2.getName());

        }
    }

    class updateClient {}

    @Nested
    class deleteClient {

        @Test
        void given_ClientId_When_deleteClient_ReturnVoid(){
           given(repository.findById(anyLong())).willReturn(Optional.of(client));

           service.deleteClient(anyLong());

           verify(repository, times(1)).deleteById(anyLong());
        }

        @Test
        void given_WrongClientId_When_deleteClient_ThrowClientNotFoundException(){
            given(repository.findById(1L)).willReturn(Optional.empty());

            var result = assertThrows(ClientNotFoundException.class,
                    ()-> {service.deleteClient(1L);});

            verify(repository, never()).deleteById(1L);
            assertEquals("Client with Id: 1 not found", result.getMessage());
        }

    }
}
