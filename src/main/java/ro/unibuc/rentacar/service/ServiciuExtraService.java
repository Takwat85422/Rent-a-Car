package ro.unibuc.rentacar.service;

import org.springframework.stereotype.Service;
import ro.unibuc.rentacar.entity.ServiciuExtra;
import ro.unibuc.rentacar.repository.ServiciuExtraRepository;

import java.util.List;

@Service
public class ServiciuExtraService {

    private final ServiciuExtraRepository repo;

    public ServiciuExtraService(ServiciuExtraRepository repo) {
        this.repo = repo;
    }

    public List<ServiciuExtra> findAll() {
        return repo.findAll();
    }

    public ServiciuExtra findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviciu inexistent: " + id));
    }

    public void save(ServiciuExtra serviciu) {
        repo.save(serviciu);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}