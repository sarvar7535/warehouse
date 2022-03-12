package pdp.uz.pricticelesson11.payload;

import lombok.Data;

@Data
public class InputDTO {
    private Integer warehouseId;

    private Integer supplierId;

    private Integer currencyId;
    private String factureNumber;

}
