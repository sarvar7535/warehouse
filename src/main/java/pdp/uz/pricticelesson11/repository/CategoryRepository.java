package pdp.uz.pricticelesson11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.pricticelesson11.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
