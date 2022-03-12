package pdp.uz.pricticelesson11.payload;

import lombok.Data;

@Data
public class OutputDTO {
    private Integer warehouseId;

    private Integer currencyId;

    private Integer clientId;
    private String factureNumber;

}
