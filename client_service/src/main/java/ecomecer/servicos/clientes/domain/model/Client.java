package ecomecer.servicos.clientes.domain.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    private Long id;

    private String name;

    private String email;

    private LocalDate birth_day;

    @Embedded
    private Address address;

}
