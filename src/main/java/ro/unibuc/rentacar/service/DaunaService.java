package ro.unibuc.rentacar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.unibuc.rentacar.entity.Dauna;
import ro.unibuc.rentacar.repository.DaunaRepository;

import java.util.List;

@Service
public class DaunaService {

    private final DaunaRepository repo;

    public DaunaService(DaunaRepository repo) {
        this.repo = repo;
    }

    public List<Dauna> findAll() {
        return repo.findAll();
    }

    public Page<Dauna> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Dauna findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dauna inexistenta: " + id));
    }

    public void save(Dauna dauna) {
        repo.save(dauna);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
