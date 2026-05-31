package ro.unibuc.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.rentacar.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}