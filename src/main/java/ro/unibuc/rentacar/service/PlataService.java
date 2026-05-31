package ro.unibuc.rentacar.service;

import org.springframework.stereotype.Service;
import ro.unibuc.rentacar.entity.Plata;
import ro.unibuc.rentacar.repository.PlataRepository;

import java.util.List;

@Service
public class PlataService {

    private final PlataRepository repo;

    public PlataService(PlataRepository repo) {
        this.repo = repo;
    }

    public List<Plata> findAll() {
        return repo.findAll();
    }

    public Plata findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plata inexistenta: " + id));
    }

    public void save(Plata plata) {
        repo.save(plata);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}