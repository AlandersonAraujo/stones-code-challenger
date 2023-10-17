package br.com.alphablack.stone.application.client;

import br.com.alphablack.stone.core.client.Client;


import java.util.Optional;

public interface ClientService {

    Client saveClient(Client client);
    Optional<Client> findById(Long id);
}
