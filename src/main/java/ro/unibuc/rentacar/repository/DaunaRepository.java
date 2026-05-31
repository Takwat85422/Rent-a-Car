package ro.unibuc.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.rentacar.entity.Dauna;

public interface DaunaRepository extends JpaRepository<Dauna, Long> {
}