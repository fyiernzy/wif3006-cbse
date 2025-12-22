package assignment.wif3006cbse.features.user.mapper;

import assignment.wif3006cbse.features.user.domain.User;
import assignment.wif3006cbse.features.user.dto.CreateUserModel;
import assignment.wif3006cbse.features.user.dto.UserModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-22T08:30:39+0800",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserModel toModel(User user) {
        if ( user == null ) {
            return null;
        }

        String id = null;
        String username = null;
        String firstName = null;
        String lastName = null;

        if ( user.getId() != null ) {
            id = user.getId();
        }
        if ( user.getUsername() != null ) {
            username = user.getUsername();
        }
        if ( user.getFirstName() != null ) {
            firstName = user.getFirstName();
        }
        if ( user.getLastName() != null ) {
            lastName = user.getLastName();
        }

        UserModel userModel = new UserModel( id, username, firstName, lastName );

        return userModel;
    }

    @Override
    public User toEntity(CreateUserModel createUserModel) {
        if ( createUserModel == null ) {
            return null;
        }

        User user = new User();

        if ( createUserModel.username() != null ) {
            user.setUsername( createUserModel.username() );
        }
        if ( createUserModel.firstName() != null ) {
            user.setFirstName( createUserModel.firstName() );
        }
        if ( createUserModel.lastName() != null ) {
            user.setLastName( createUserModel.lastName() );
        }
        if ( createUserModel.password() != null ) {
            user.setPassword( createUserModel.password() );
        }

        return user;
    }
}
