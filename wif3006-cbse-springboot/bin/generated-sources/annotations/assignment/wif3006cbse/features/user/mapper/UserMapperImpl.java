package assignment.wif3006cbse.features.user.mapper;

import assignment.wif3006cbse.features.user.domain.User;
import assignment.wif3006cbse.features.user.domain.UserRole;
import assignment.wif3006cbse.features.user.dto.CreateUserModel;
import assignment.wif3006cbse.features.user.dto.UserModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-03T17:31:40+0800",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
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
        String email = null;
        UserRole role = null;
        byte[] profilePic = null;
        String headline = null;
        String city = null;
        String state = null;
        List<String> categories = null;
        String university = null;
        List<String> skills = null;
        String about = null;
        String rating = null;
        List<String> favoriteProjects = null;
        List<String> takenProjects = null;
        List<String> applyingProjects = null;
        List<String> completedProjects = null;
        List<String> postedProjects = null;
        List<String> experienceIds = null;
        List<String> productIds = null;

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
        if ( user.getEmail() != null ) {
            email = user.getEmail();
        }
        if ( user.getRole() != null ) {
            role = user.getRole();
        }
        byte[] profilePic1 = user.getProfilePic();
        if ( profilePic1 != null ) {
            profilePic = Arrays.copyOf( profilePic1, profilePic1.length );
        }
        if ( user.getHeadline() != null ) {
            headline = user.getHeadline();
        }
        if ( user.getCity() != null ) {
            city = user.getCity();
        }
        if ( user.getState() != null ) {
            state = user.getState();
        }
        List<String> list = user.getCategories();
        if ( list != null ) {
            categories = new ArrayList<String>( list );
        }
        if ( user.getUniversity() != null ) {
            university = user.getUniversity();
        }
        List<String> list1 = user.getSkills();
        if ( list1 != null ) {
            skills = new ArrayList<String>( list1 );
        }
        if ( user.getAbout() != null ) {
            about = user.getAbout();
        }
        if ( user.getRating() != null ) {
            rating = user.getRating();
        }
        List<String> list2 = user.getFavoriteProjects();
        if ( list2 != null ) {
            favoriteProjects = new ArrayList<String>( list2 );
        }
        List<String> list3 = user.getTakenProjects();
        if ( list3 != null ) {
            takenProjects = new ArrayList<String>( list3 );
        }
        List<String> list4 = user.getApplyingProjects();
        if ( list4 != null ) {
            applyingProjects = new ArrayList<String>( list4 );
        }
        List<String> list5 = user.getCompletedProjects();
        if ( list5 != null ) {
            completedProjects = new ArrayList<String>( list5 );
        }
        List<String> list6 = user.getPostedProjects();
        if ( list6 != null ) {
            postedProjects = new ArrayList<String>( list6 );
        }
        List<String> list7 = user.getExperienceIds();
        if ( list7 != null ) {
            experienceIds = new ArrayList<String>( list7 );
        }
        List<String> list8 = user.getProductIds();
        if ( list8 != null ) {
            productIds = new ArrayList<String>( list8 );
        }

        UserModel userModel = new UserModel( id, username, firstName, lastName, email, role, profilePic, headline, city, state, categories, university, skills, about, rating, favoriteProjects, takenProjects, applyingProjects, completedProjects, postedProjects, experienceIds, productIds );

        return userModel;
    }

    @Override
    public User toEntity(CreateUserModel createUserModel) {
        if ( createUserModel == null ) {
            return null;
        }

        User user = new User();

        if ( createUserModel.about() != null ) {
            user.setAbout( createUserModel.about() );
        }
        List<String> list = createUserModel.applyingProjects();
        if ( list != null ) {
            user.setApplyingProjects( new ArrayList<String>( list ) );
        }
        List<String> list1 = createUserModel.categories();
        if ( list1 != null ) {
            user.setCategories( new ArrayList<String>( list1 ) );
        }
        if ( createUserModel.city() != null ) {
            user.setCity( createUserModel.city() );
        }
        List<String> list2 = createUserModel.completedProjects();
        if ( list2 != null ) {
            user.setCompletedProjects( new ArrayList<String>( list2 ) );
        }
        if ( createUserModel.email() != null ) {
            user.setEmail( createUserModel.email() );
        }
        List<String> list3 = createUserModel.experienceIds();
        if ( list3 != null ) {
            user.setExperienceIds( new ArrayList<String>( list3 ) );
        }
        List<String> list4 = createUserModel.favoriteProjects();
        if ( list4 != null ) {
            user.setFavoriteProjects( new ArrayList<String>( list4 ) );
        }
        if ( createUserModel.firstName() != null ) {
            user.setFirstName( createUserModel.firstName() );
        }
        if ( createUserModel.headline() != null ) {
            user.setHeadline( createUserModel.headline() );
        }
        if ( createUserModel.lastName() != null ) {
            user.setLastName( createUserModel.lastName() );
        }
        if ( createUserModel.password() != null ) {
            user.setPassword( createUserModel.password() );
        }
        List<String> list5 = createUserModel.postedProjects();
        if ( list5 != null ) {
            user.setPostedProjects( new ArrayList<String>( list5 ) );
        }
        List<String> list6 = createUserModel.productIds();
        if ( list6 != null ) {
            user.setProductIds( new ArrayList<String>( list6 ) );
        }
        byte[] profilePic = createUserModel.profilePic();
        if ( profilePic != null ) {
            user.setProfilePic( Arrays.copyOf( profilePic, profilePic.length ) );
        }
        if ( createUserModel.rating() != null ) {
            user.setRating( createUserModel.rating() );
        }
        if ( createUserModel.role() != null ) {
            user.setRole( createUserModel.role() );
        }
        List<String> list7 = createUserModel.skills();
        if ( list7 != null ) {
            user.setSkills( new ArrayList<String>( list7 ) );
        }
        if ( createUserModel.state() != null ) {
            user.setState( createUserModel.state() );
        }
        List<String> list8 = createUserModel.takenProjects();
        if ( list8 != null ) {
            user.setTakenProjects( new ArrayList<String>( list8 ) );
        }
        if ( createUserModel.university() != null ) {
            user.setUniversity( createUserModel.university() );
        }
        if ( createUserModel.username() != null ) {
            user.setUsername( createUserModel.username() );
        }

        return user;
    }
}
