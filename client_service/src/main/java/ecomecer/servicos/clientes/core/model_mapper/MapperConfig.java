package ecomecer.servicos.clientes.core.model_mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Configuration
public class MapperConfig {
    @Bean
    ModelMapper config(){
        return new ModelMapper();
    }
}
