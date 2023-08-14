package restaurant.bangtanPalace.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.bangtanPalace.Service.UserService;
import restaurant.bangtanPalace.domain.User;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> listAll(){
        return ResponseEntity.ok(userService.listAll());
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid User user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
}