package pdp.uz.pricticelesson11.payload;

import lombok.Data;

@Data
public class CategoryDTO {
    private String name;
    private Integer parentCategoryId;
}
