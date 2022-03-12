package pdp.uz.pricticelesson11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.pricticelesson11.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
