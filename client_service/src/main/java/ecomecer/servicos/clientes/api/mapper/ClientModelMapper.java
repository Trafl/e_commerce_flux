package ecomecer.servicos.clientes.api.mapper;

import ecomecer.servicos.clientes.domain.dto.ClientDTOInput;
import ecomecer.servicos.clientes.domain.dto.ClientDTOOutput;
import ecomecer.servicos.clientes.domain.model.Client;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientModelMapper {

    private final ModelMapper mapper;

    public Client toModel(ClientDTOInput input){
        return mapper.map(input, Client.class);
    }

    public ClientDTOOutput toDto(Client client){
        return mapper.map(client, ClientDTOOutput.class);
    }
}
