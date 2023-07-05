package restaurant.bangtanPalace.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class FoodPutRequestBody {
    private String title;
    private String image;
    private Integer price;

}