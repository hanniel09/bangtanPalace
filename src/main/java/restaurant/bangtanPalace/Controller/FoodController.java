package restaurant.bangtanPalace.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import restaurant.bangtanPalace.Service.FoodService;
import restaurant.bangtanPalace.Service.UserService;
import restaurant.bangtanPalace.domain.Food;
import restaurant.bangtanPalace.exception.BadRequestException;
import restaurant.bangtanPalace.request.Food.FoodPostRequestBody;
import restaurant.bangtanPalace.request.Food.FoodPutRequestBody;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("cardapio")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<Page<Food>> list(Pageable pageable) {
        return ResponseEntity.ok(foodService.listAll(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Food> findById(@PathVariable long id) {
        return ResponseEntity.ok(foodService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/foods")
    public ResponseEntity<List<Food>> listAll() {
        return ResponseEntity.ok(foodService.listAllNonPageable());
    }

    @PostMapping()
    public ResponseEntity<Food> save(@RequestBody @Valid FoodPostRequestBody foodPostRequestBody, Authentication authentication) throws IOException {
        return new ResponseEntity<>(foodService.save(foodPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        foodService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody FoodPutRequestBody foodPutRequestBody) {
        foodService.replace(foodPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
