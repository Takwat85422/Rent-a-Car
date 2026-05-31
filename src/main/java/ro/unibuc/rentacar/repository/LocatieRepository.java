package ro.unibuc.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.rentacar.entity.Locatie;

public interface LocatieRepository extends JpaRepository<Locatie, Long> {
}