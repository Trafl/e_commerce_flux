package ecomecer.servicos.clientes.domain.dto;

import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTOInput {

    private String name;

    private String email;

    private LocalDate birth_day;

    private AddressDTO address;
}
