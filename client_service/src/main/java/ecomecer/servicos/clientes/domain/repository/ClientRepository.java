package ecomecer.servicos.clientes.domain.repository;

import ecomecer.servicos.clientes.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
