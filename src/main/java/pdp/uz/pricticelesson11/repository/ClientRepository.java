package pdp.uz.pricticelesson11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.pricticelesson11.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
