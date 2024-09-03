package ecomecer.servicos.clientes.domain.dto;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    private String street;

    private String city;

    private Integer number;

    private Integer postal_number;
}
