package ecomecer.servicos.clientes.domain.dto;

import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTOInput {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotNull
    private LocalDate born_day;

    @Valid
    private AddressDTO address;
}
