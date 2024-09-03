package ecomecer.servicos.clientes.core.model_mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
@RequiredArgsConstructor
public class MapperConfig {

    private final ModelMapper mapper;

    @Bean
    ModelMapper config(){
        return mapper;
    }
}
