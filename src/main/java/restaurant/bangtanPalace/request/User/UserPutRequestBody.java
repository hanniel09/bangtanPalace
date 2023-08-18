package restaurant.bangtanPalace.request.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPutRequestBody {
    private Long id;
    private String username;
    private String password;
    private String userjwt;
}
