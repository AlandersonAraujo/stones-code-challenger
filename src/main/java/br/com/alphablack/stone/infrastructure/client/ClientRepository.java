package br.com.alphablack.stone.infrastructure.client;

import br.com.alphablack.stone.core.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findById(Long id);
}
