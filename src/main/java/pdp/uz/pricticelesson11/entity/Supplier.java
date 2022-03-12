package pdp.uz.pricticelesson11.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pdp.uz.pricticelesson11.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Supplier extends AbsEntity {

    @Column(unique = true, nullable = false)
    private String phoneNumber;

}
