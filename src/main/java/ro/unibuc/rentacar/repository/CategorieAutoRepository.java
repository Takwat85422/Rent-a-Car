package ro.unibuc.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.rentacar.entity.CategorieAuto;

public interface CategorieAutoRepository extends JpaRepository<CategorieAuto, Long> {
}
