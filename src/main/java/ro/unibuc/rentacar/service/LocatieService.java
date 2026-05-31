package ro.unibuc.rentacar.service;

import org.springframework.stereotype.Service;
import ro.unibuc.rentacar.entity.Locatie;
import ro.unibuc.rentacar.repository.LocatieRepository;

import java.util.List;

@Service
public class LocatieService {

    private final LocatieRepository repo;

    public LocatieService(LocatieRepository repo) {
        this.repo = repo;
    }

    public List<Locatie> findAll() {
        return repo.findAll();
    }

    public Locatie findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Locatie inexistenta: " + id));
    }

    public void save(Locatie locatie) {
        repo.save(locatie);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}