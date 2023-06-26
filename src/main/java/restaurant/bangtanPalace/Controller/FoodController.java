package restaurant.bangtanPalace.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.bangtanPalace.Service.FoodService;
import restaurant.bangtanPalace.domain.Food;
import restaurant.bangtanPalace.request.FoodPostRequestBody;

import java.util.List;

@RestController
@RequestMapping("cardapio")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;
    @GetMapping()
    public ResponseEntity<Page<Food>> list(Pageable pageable){
        return ResponseEntity.ok(foodService.listAll(pageable));
    }
    @GetMapping(path = "/foods")
    public ResponseEntity<List<Food>> listAll() {
        return ResponseEntity.ok(foodService.listAllNonPageable());
    }
    @PostMapping
    public ResponseEntity<Food> save(@RequestBody @Valid FoodPostRequestBody foodPostRequestBody){
        return new ResponseEntity<>(foodService.save(foodPostRequestBody), HttpStatus.OK);

    }
}
