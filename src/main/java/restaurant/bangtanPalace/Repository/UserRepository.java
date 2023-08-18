package restaurant.bangtanPalace.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import restaurant.bangtanPalace.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);
}
