package ro.unibuc.rentacar.service;

import org.springframework.stereotype.Service;
import ro.unibuc.rentacar.entity.PermisConducere;
import ro.unibuc.rentacar.repository.PermisConducereRepository;

import java.util.List;

@Service
public class PermisConducereService {

    private final PermisConducereRepository repo;

    public PermisConducereService(PermisConducereRepository repo) {
        this.repo = repo;
    }

    public List<PermisConducere> findAll() {
        return repo.findAll();
    }

    public PermisConducere findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Permis inexistent: " + id));
    }

    public void save(PermisConducere permis) {
        repo.save(permis);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}