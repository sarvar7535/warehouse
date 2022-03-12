package pdp.uz.pricticelesson11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.pricticelesson11.entity.Currency;
import pdp.uz.pricticelesson11.entity.Warehouse;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    boolean existsByName(String name);
}
