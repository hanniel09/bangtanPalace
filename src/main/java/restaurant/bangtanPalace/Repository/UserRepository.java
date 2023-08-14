package restaurant.bangtanPalace.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.bangtanPalace.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
