package pdp.uz.pricticelesson11.payload;

import lombok.Data;

@Data
public class InputProductDTO {
    private Integer productId;
    private Double amount;
    private double price;
    private Integer inputId;

}
