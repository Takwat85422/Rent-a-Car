package ro.unibuc.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.rentacar.entity.PermisConducere;

public interface PermisConducereRepository extends JpaRepository<PermisConducere, Long> {
}