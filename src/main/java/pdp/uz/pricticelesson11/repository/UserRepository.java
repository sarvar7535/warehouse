package pdp.uz.pricticelesson11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.pricticelesson11.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByPhoneNumberAndPassword(String phoneNumber, String password);

}
