package assignment.wif3006cbse.features.user.mapper;

import assignment.wif3006cbse.config.MapStructConfig;
import assignment.wif3006cbse.features.user.domain.User;
import assignment.wif3006cbse.features.user.dto.CreateUserModel;
import assignment.wif3006cbse.features.user.dto.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class)
public interface UserMapper {

    UserModel toModel(User user);

    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User toEntity(CreateUserModel createUserModel);
}
