package restaurant.bangtanPalace.request.Food;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class FoodPutRequestBody {
    private Long id;
    private String title;
    private String image;
    private Float price;

}
