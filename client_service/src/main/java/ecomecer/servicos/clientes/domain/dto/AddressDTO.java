package ecomecer.servicos.clientes.domain.dto;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotNull
    private Integer number;

    @NotNull
    private Integer postal_number;
}
