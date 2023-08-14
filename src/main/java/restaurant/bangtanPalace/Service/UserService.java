package restaurant.bangtanPalace.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import restaurant.bangtanPalace.Repository.UserRepository;
import restaurant.bangtanPalace.domain.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> listAll(){
        return userRepository.findAll();
    }

    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }
}
