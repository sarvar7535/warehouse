package pdp.uz.pricticelesson11.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    private String firstName;

    private String lastName;
    private String phoneNumber;


    private Integer[] warehouseList;

    private String password;

}
