package restaurant.bangtanPalace.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import restaurant.bangtanPalace.domain.Food;
import restaurant.bangtanPalace.request.Food.FoodPostRequestBody;
import restaurant.bangtanPalace.request.Food.FoodPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class FoodMapper {
    public static final FoodMapper INSTANCE = Mappers.getMapper(FoodMapper.class);

    public abstract Food toFood(FoodPostRequestBody foodPostRequestBody);

    public abstract Food toFood(FoodPutRequestBody foodPutRequestBody);
}
