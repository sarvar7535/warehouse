package pdp.uz.pricticelesson11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.pricticelesson11.entity.Input;
import pdp.uz.pricticelesson11.entity.Output;

public interface OutputRepository extends JpaRepository<Output,Integer> {
    boolean existsByFactureNumber(String factureNumber);
}
