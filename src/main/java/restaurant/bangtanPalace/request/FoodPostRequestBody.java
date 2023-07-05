package restaurant.bangtanPalace.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodPostRequestBody {
    @NotEmpty(message = "The food name cannot be empty")
    private String title;
    private String image;
    private Integer price;
}

