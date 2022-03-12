package pdp.uz.pricticelesson11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.pricticelesson11.entity.Input;

public interface InputRepository extends JpaRepository<Input,Integer> {
    boolean existsByFactureNumber(String factureNumber);
}
