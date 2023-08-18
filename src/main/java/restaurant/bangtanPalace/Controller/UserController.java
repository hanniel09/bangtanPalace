package restaurant.bangtanPalace.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import restaurant.bangtanPalace.Repository.UserRepository;
import restaurant.bangtanPalace.Service.UserService;
import restaurant.bangtanPalace.domain.User;
import restaurant.bangtanPalace.mapper.UserMapper;
import restaurant.bangtanPalace.request.User.UserPostRequestBody;
import restaurant.bangtanPalace.request.User.UserPutRequestBody;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository repository;

    @GetMapping()
    public ResponseEntity<List<User>> listAll(){
        return ResponseEntity.ok(userService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> findById(@PathVariable long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserPostRequestBody data){
        if(this.repository.findByUsername(data.username()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.username(), encryptedPassword, data.role());
        repository.save(UserMapper.INSTANCE.toUser(data));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UserPutRequestBody userPutRequestBody){
        userService.update(userPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
