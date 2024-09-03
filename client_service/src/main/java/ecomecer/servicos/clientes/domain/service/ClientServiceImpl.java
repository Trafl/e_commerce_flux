package ecomecer.servicos.clientes.domain.service;

import ecomecer.servicos.clientes.domain.exception.ClientNotFoundException;
import ecomecer.servicos.clientes.domain.model.Client;
import ecomecer.servicos.clientes.domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class ClientServiceImpl implements ClientService{

    private final ClientRepository repository;

    private final String logTime = LocalDateTime.now().toString();

    @Override
    public Client addClient(Client client) {
        log.info("[{}] - ClientServiceImpl executing addClient", logTime);

        var newClient = repository.save(client);

        log.info("[{}] - ClientServiceImpl saved client successful", logTime);
        return newClient;
    }

    @Override
    public Client getClient(Long id) {
        log.info("[{}] - ClientServiceImpl executing getClient with id [{}]", logTime, id);
        return  repository.findById(id)
                .orElseThrow(
                        ()-> new ClientNotFoundException(
                                String.format("Client with Id: %d not found", id)));
    }

    @Override
    public Page<Client> getAllClients(Pageable pageable) {
        log.info("[{}] - ClientServiceImpl executing getAllClients", logTime);
        return repository.findAll(pageable);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        return null;
    }

    @Override
    public void deleteClient(Long id) {
        log.info("[{}] - ClientServiceImpl executing deleteClient with id [{}]", logTime, id);
        getClient(id);
        repository.deleteById(id);
        log.info("[{}] - ClientServiceImpl deleted client successful", logTime);
    }
}
