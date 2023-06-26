package restaurant.bangtanPalace.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import restaurant.bangtanPalace.Repository.FoodRepository;
import restaurant.bangtanPalace.domain.Food;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    public Page<Food> listAll(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }
}
