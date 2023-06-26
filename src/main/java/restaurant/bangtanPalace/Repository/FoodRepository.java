package restaurant.bangtanPalace.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurant.bangtanPalace.domain.Food;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByTitle(String title);
}
