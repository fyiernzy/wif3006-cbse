package assignment.wif3006cbse.features.profile.mapper;

import assignment.wif3006cbse.features.profile.domain.entity.User;
import assignment.wif3006cbse.features.profile.dto.user.CreateUserModel;
import assignment.wif3006cbse.features.profile.dto.user.PublicUserModel;
import assignment.wif3006cbse.features.profile.dto.user.UpdateSkillsModel;
import assignment.wif3006cbse.features.profile.dto.user.UpdateUserModel;
import assignment.wif3006cbse.features.profile.dto.user.UserModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-03T21:43:50+0800",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class ProfileUserMapperImpl implements ProfileUserMapper {

    @Override
    public UserModel toModel(User user) {
        if ( user == null ) {
            return null;
        }

        boolean isProfilePublic = false;
        String id = null;
        String email = null;
        String name = null;
        String about = null;
        String location = null;
        List<String> categories = null;
        List<String> skills = null;

        isProfilePublic = user.isProfilePublic();
        if ( user.getId() != null ) {
            id = user.getId();
        }
        if ( user.getEmail() != null ) {
            email = user.getEmail();
        }
        if ( user.getName() != null ) {
            name = user.getName();
        }
        if ( user.getAbout() != null ) {
            about = user.getAbout();
        }
        if ( user.getLocation() != null ) {
            location = user.getLocation();
        }
        List<String> list = user.getCategories();
        if ( list != null ) {
            categories = new ArrayList<String>( list );
        }
        List<String> list1 = user.getSkills();
        if ( list1 != null ) {
            skills = new ArrayList<String>( list1 );
        }

        UserModel userModel = new UserModel( id, email, name, about, location, categories, skills, isProfilePublic );

        return userModel;
    }

    @Override
    public PublicUserModel toPublicModel(User user) {
        if ( user == null ) {
            return null;
        }

        String id = null;
        String name = null;
        String about = null;
        String location = null;
        List<String> categories = null;
        List<String> skills = null;

        if ( user.getId() != null ) {
            id = user.getId();
        }
        if ( user.getName() != null ) {
            name = user.getName();
        }
        if ( user.getAbout() != null ) {
            about = user.getAbout();
        }
        if ( user.getLocation() != null ) {
            location = user.getLocation();
        }
        List<String> list = user.getCategories();
        if ( list != null ) {
            categories = new ArrayList<String>( list );
        }
        List<String> list1 = user.getSkills();
        if ( list1 != null ) {
            skills = new ArrayList<String>( list1 );
        }

        PublicUserModel publicUserModel = new PublicUserModel( id, name, about, location, categories, skills );

        return publicUserModel;
    }

    @Override
    public User toEntity(CreateUserModel createUserModel) {
        if ( createUserModel == null ) {
            return null;
        }

        User user = new User();

        if ( createUserModel.isProfilePublic() != null ) {
            user.setProfilePublic( createUserModel.isProfilePublic() );
        }
        if ( createUserModel.email() != null ) {
            user.setEmail( createUserModel.email() );
        }
        if ( createUserModel.name() != null ) {
            user.setName( createUserModel.name() );
        }
        if ( createUserModel.about() != null ) {
            user.setAbout( createUserModel.about() );
        }
        if ( createUserModel.location() != null ) {
            user.setLocation( createUserModel.location() );
        }
        List<String> list = createUserModel.categories();
        if ( list != null ) {
            user.setCategories( new ArrayList<String>( list ) );
        }
        List<String> list1 = createUserModel.skills();
        if ( list1 != null ) {
            user.setSkills( new ArrayList<String>( list1 ) );
        }

        return user;
    }

    @Override
    public void updateEntityFromUpdateModel(User user, UpdateUserModel updateUserModel) {
        if ( updateUserModel == null ) {
            return;
        }

        if ( updateUserModel.name() != null ) {
            user.setName( updateUserModel.name() );
        }
        if ( updateUserModel.about() != null ) {
            user.setAbout( updateUserModel.about() );
        }
        if ( updateUserModel.location() != null ) {
            user.setLocation( updateUserModel.location() );
        }
        if ( user.getCategories() != null ) {
            List<String> list = updateUserModel.categories();
            if ( list != null ) {
                user.getCategories().clear();
                user.getCategories().addAll( list );
            }
        }
        else {
            List<String> list = updateUserModel.categories();
            if ( list != null ) {
                user.setCategories( new ArrayList<String>( list ) );
            }
        }
    }

    @Override
    public void updateSkillsFromModel(User user, UpdateSkillsModel updateSkillsModel) {
        if ( updateSkillsModel == null ) {
            return;
        }

        if ( user.getSkills() != null ) {
            List<String> list = updateSkillsModel.skills();
            if ( list != null ) {
                user.getSkills().clear();
                user.getSkills().addAll( list );
            }
        }
        else {
            List<String> list = updateSkillsModel.skills();
            if ( list != null ) {
                user.setSkills( new ArrayList<String>( list ) );
            }
        }
    }
}
