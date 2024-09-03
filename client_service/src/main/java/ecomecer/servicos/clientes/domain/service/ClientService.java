package ecomecer.servicos.clientes.domain.service;

import ecomecer.servicos.clientes.domain.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    Client addClient(Client client);

    Client getClient(Long id);

    Page<Client> getAllClients(Pageable pageable);

    Client updateClient(Long id, Client client);

    void deleteClient(Long id);
}
