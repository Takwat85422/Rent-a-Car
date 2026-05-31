package ro.unibuc.rentacar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.unibuc.rentacar.entity.Autovehicul;
import ro.unibuc.rentacar.repository.AutovehiculRepository;

import java.util.List;

@Service
public class AutovehiculService {

    private final AutovehiculRepository repo;

    public AutovehiculService(AutovehiculRepository repo) {
        this.repo = repo;
    }

    public List<Autovehicul> findAll() {
        return repo.findAll();
    }

    public Page<Autovehicul> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Autovehicul findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Autovehicul inexistent: " + id));
    }

    public void save(Autovehicul autovehicul) {
        repo.save(autovehicul);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
