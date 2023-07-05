package restaurant.bangtanPalace.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import restaurant.bangtanPalace.Repository.FoodRepository;
import restaurant.bangtanPalace.domain.Food;
import restaurant.bangtanPalace.exception.BadRequestException;
import restaurant.bangtanPalace.mapper.FoodMapper;
import restaurant.bangtanPalace.request.FoodPostRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    public Page<Food> listAll(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

    public List<Food> listAllNonPageable() {
        return foodRepository.findAll();
    }

    public Food findByIdOrThrowBadRequestException(long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Food Id not found"));
    }

    @Transactional
    public Food save(FoodPostRequestBody foodPostRequestBody){
        return foodRepository.save(FoodMapper.INSTANCE.toFood(foodPostRequestBody));
    }


    public void delete(long id) {
        foodRepository.delete(findByIdOrThrowBadRequestException(id));
    }
}
