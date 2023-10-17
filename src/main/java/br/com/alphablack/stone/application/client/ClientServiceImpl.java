package br.com.alphablack.stone.application.client;

import br.com.alphablack.stone.core.client.Client;
import br.com.alphablack.stone.infrastructure.client.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client saveClient(Client client) {
        return this.clientRepository.save(client);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return this.clientRepository.findById(id);
    }
}
