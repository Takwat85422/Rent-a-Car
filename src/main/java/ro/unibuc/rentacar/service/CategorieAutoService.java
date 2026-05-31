package ro.unibuc.rentacar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.unibuc.rentacar.entity.CategorieAuto;
import ro.unibuc.rentacar.repository.CategorieAutoRepository;

import java.util.List;

@Service
public class CategorieAutoService {

    private final CategorieAutoRepository repo;

    public CategorieAutoService(CategorieAutoRepository repo) {
        this.repo = repo;
    }

    public List<CategorieAuto> findAll() {
        return repo.findAll();
    }

    public Page<CategorieAuto> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public CategorieAuto findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categorie inexistenta: " + id));
    }

    public void save(CategorieAuto categorie) {
        repo.save(categorie);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
