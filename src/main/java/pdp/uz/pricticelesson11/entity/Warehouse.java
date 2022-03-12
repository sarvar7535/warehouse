package pdp.uz.pricticelesson11.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pdp.uz.pricticelesson11.entity.template.AbsEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Warehouse extends AbsEntity {

}
