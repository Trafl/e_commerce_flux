package ecomecer.servicos.clientes.api.controller;

import ecomecer.servicos.clientes.api.mapper.ClientModelMapper;
import ecomecer.servicos.clientes.domain.dto.ClientDTOInput;
import ecomecer.servicos.clientes.domain.dto.ClientDTOOutput;
import ecomecer.servicos.clientes.domain.model.Client;
import ecomecer.servicos.clientes.domain.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/client")
@Log4j2
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    private final ClientModelMapper mapper;

    final private PagedResourcesAssembler<ClientDTOOutput> pagedResourcesAssembler;
    private final String logTime = LocalDateTime.now().toString();

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ClientDTOOutput>>> getAllClients(@PageableDefault(size = 10) Pageable pageable,
                                                                                  HttpServletRequest request){
        log.info("[{}] - [ClientController] IP: {}, Request: GET, EndPoint: 'api/client'", logTime, request.getRemoteAddr());

        Page<Client> clientsPage = service.getAllClients(pageable);
        Page<ClientDTOOutput> clientsPageDto = mapper.toPageDTO(clientsPage);

        PagedModel<EntityModel<ClientDTOOutput>> paged = pagedResourcesAssembler.toModel(clientsPageDto);

        return ResponseEntity.ok(paged);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDTOOutput> getOneClientById(@PathVariable Long clientId, HttpServletRequest request){
        log.info("[{}] - [ClientController] IP: {}, Request: GET, EndPoint: 'api/client/{}'", logTime, request.getRemoteAddr(), clientId);

        var client = service.getClient(clientId);

        var clientDto = mapper.toDto(client);

        return ResponseEntity.ok(clientDto);
    }

    @PostMapping()
    public ResponseEntity<ClientDTOOutput> addClient(@Valid @RequestBody ClientDTOInput input, HttpServletRequest request){
        log.info("[{}] - [ClientController] IP: {}, Request: POST, EndPoint: 'api/client'", logTime, request.getRemoteAddr());
        var client = mapper.toModel(input);
        var newClient = service.addClient(client);

        var clientDto = mapper.toDto(newClient);

        return ResponseEntity.status(HttpStatus.CREATED).body(clientDto);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientDTOOutput> updateClientById(@PathVariable Long clientId,@Valid @RequestBody ClientDTOInput input, HttpServletRequest request){
        log.info("[{}] - [ClientController] IP: {}, Request: PUT, EndPoint: 'api/client/{}'", logTime, request.getRemoteAddr(), clientId);
        var client = service.updateClient(clientId, input);
        var clientDTO = mapper.toDto(client);

        return ResponseEntity.ok(clientDTO);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Long clientId, HttpServletRequest request){
        log.info("[{}] - [ClientController] IP: {}, Request: DELETE, EndPoint: 'api/client/{}'", logTime, request.getRemoteAddr(), clientId);
        service.deleteClient(clientId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
