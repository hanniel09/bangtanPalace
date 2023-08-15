package restaurant.bangtanPalace.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import restaurant.bangtanPalace.Repository.UserRepository;
import restaurant.bangtanPalace.domain.User;
import restaurant.bangtanPalace.exception.BadRequestException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User id not found"));
    }

    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.delete(findById(id));
    }
}
