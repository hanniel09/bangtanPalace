package restaurant.bangtanPalace.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import restaurant.bangtanPalace.domain.User;
import restaurant.bangtanPalace.request.User.UserPostRequestBody;
import restaurant.bangtanPalace.request.User.UserPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User toUser(UserPostRequestBody userPostRequestBody);

    public abstract User toUser(UserPutRequestBody userPutRequestBody);
}
