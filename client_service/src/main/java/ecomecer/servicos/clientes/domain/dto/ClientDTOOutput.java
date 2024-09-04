package ecomecer.servicos.clientes.domain.dto;

import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientDTOOutput {

    private Long id;

    private String name;

    private String email;

    private LocalDate born_day;

    private AddressDTO address;
}
