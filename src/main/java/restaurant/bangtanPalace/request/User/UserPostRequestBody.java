package restaurant.bangtanPalace.request.User;

import restaurant.bangtanPalace.domain.UserRole;

public record UserPostRequestBody(String username, String password, UserRole role) {

}
