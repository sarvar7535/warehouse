package pdp.uz.pricticelesson11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.pricticelesson11.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    boolean existsByName(String name);

}
