package ro.unibuc.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.rentacar.entity.Utilizator;

import java.util.Optional;

public interface UtilizatorRepository extends JpaRepository<Utilizator, Long> {
    Optional<Utilizator> findByUsername(String username);
}