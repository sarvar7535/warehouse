package pdp.uz.pricticelesson11.payload;

import lombok.Data;

@Data
public class ApiResponse {
    private String massage;
    private boolean success;
    private Object object;

    public ApiResponse(String massage, boolean success) {
        this.massage = massage;
        this.success = success;
    }

    public ApiResponse(String massage, boolean success, Object object) {
        this.massage = massage;
        this.success = success;
        this.object = object;
    }
}
