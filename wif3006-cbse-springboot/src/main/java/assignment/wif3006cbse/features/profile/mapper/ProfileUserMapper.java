package assignment.wif3006cbse.features.profile.mapper;

import assignment.wif3006cbse.config.MapStructConfig;
import assignment.wif3006cbse.features.profile.domain.entity.User;
import assignment.wif3006cbse.features.profile.dto.user.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface ProfileUserMapper {

    @Mapping(source = "profilePublic", target = "isProfilePublic")
    UserModel toModel(User user);

    PublicUserModel toPublicModel(User user);

    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(source = "isProfilePublic", target = "profilePublic")
    User toEntity(CreateUserModel createUserModel);

    @Mapping(target = "email", ignore = true)
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "profilePublic", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromUpdateModel(@MappingTarget User user, UpdateUserModel updateUserModel);

    @Mapping(target = "email", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "about", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "profilePublic", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateSkillsFromModel(@MappingTarget User user, UpdateSkillsModel updateSkillsModel);
}
