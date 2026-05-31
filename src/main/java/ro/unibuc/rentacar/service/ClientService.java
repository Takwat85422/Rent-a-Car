package ro.unibuc.rentacar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.unibuc.rentacar.entity.Client;
import ro.unibuc.rentacar.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository repo;

    public ClientService(ClientRepository repo) {
        this.repo = repo;
    }

    public List<Client> findAll() {
        return repo.findAll();
    }

    public Page<Client> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Client findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client inexistent: " + id));
    }

    public void save(Client client) {
        repo.save(client);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
