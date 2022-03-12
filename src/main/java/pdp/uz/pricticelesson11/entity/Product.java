package pdp.uz.pricticelesson11.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pdp.uz.pricticelesson11.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Product extends AbsEntity {

    @ManyToOne(optional = false)
    private Category category;

    @OneToOne
    private Attachment attachment;

    private String code;

    @ManyToOne
    private Measurement measurement;

}
