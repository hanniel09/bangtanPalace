package restaurant.bangtanPalace.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restaurant.bangtanPalace.Service.FoodService;
import restaurant.bangtanPalace.domain.Food;

@RestController
@RequestMapping("cardapio")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;
    @GetMapping
    public ResponseEntity<Page<Food>> list(Pageable pageable){
        return ResponseEntity.ok(foodService.listAll(pageable));
    }
}
