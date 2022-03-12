package pdp.uz.pricticelesson11.payload;

import lombok.Data;

@Data
public class OutputProductDTO {
    private Integer productId;
    private Double amount;
    private double price;
    private Integer outputId;

}
