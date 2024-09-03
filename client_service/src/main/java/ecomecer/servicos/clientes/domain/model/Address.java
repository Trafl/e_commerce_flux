package ecomecer.servicos.clientes.domain.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Embeddable
public class Address {

    private String street;

    private String city;

    private Integer number;

    private Integer postal_number;
}
