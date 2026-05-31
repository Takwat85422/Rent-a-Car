package ro.unibuc.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.rentacar.entity.ContractInchiriere;

public interface ContractInchiriereRepository extends JpaRepository<ContractInchiriere, Long> {
}