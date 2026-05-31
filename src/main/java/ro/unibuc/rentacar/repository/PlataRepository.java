package ro.unibuc.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.rentacar.entity.Plata;

public interface PlataRepository extends JpaRepository<Plata, Long> {
}