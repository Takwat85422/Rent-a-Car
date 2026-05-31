package ro.unibuc.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.rentacar.entity.Autovehicul;

public interface AutovehiculRepository extends JpaRepository<Autovehicul, Long> {
}
