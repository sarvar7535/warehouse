package pdp.uz.pricticelesson11.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pdp.uz.pricticelesson11.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Category extends AbsEntity {

    @ManyToOne
    private Category parentCategory;
}
