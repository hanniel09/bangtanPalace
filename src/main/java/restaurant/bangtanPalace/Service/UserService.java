package restaurant.bangtanPalace.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import restaurant.bangtanPalace.Repository.UserRepository;
import restaurant.bangtanPalace.domain.User;
import restaurant.bangtanPalace.exception.BadRequestException;
import restaurant.bangtanPalace.mapper.UserMapper;
import restaurant.bangtanPalace.request.User.UserPostRequestBody;
import restaurant.bangtanPalace.request.User.UserPutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User id not found"));
    }

    @Transactional
    public User save(UserPostRequestBody userPostRequestBody){
        return userRepository.save(UserMapper.INSTANCE.toUser(userPostRequestBody));
    }

    public void delete(Long id){
        userRepository.delete(findById(id));
    }

    public void update(UserPutRequestBody userPutRequestBody){
        User savedUser = findById(userPutRequestBody.getId());
        User user = UserMapper.INSTANCE.toUser(userPutRequestBody);
        user.setId(savedUser.getId());
        userRepository.save(user);
    }

}
