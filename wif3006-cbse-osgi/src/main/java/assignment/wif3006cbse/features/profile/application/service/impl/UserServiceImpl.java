package assignment.wif3006cbse.features.profile.application.service.impl;

import assignment.wif3006cbse.features.profile.application.dto.user.*;
import assignment.wif3006cbse.features.profile.application.service.UserService;
import assignment.wif3006cbse.features.profile.domain.entity.User;
import assignment.wif3006cbse.features.profile.domain.repository.UserRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;

@Component(service = UserService.class)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Activate
    public UserServiceImpl(@Reference UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserServiceImpl() {
        this.userRepository = null;
    }

    @Override
    public UserModel createUser(CreateUserModel createUserModel) {
        User user = new User(createUserModel.email(), createUserModel.name());
        user.setAbout(createUserModel.about());
        user.setLocation(createUserModel.location());
        if (createUserModel.categories() != null) {
            user.setCategories(new ArrayList<>(createUserModel.categories()));
        }
        User saved = userRepository.save(user);
        return toModel(saved);
    }

    @Override
    public UserModel findUserById(String id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
        return toModel(user);
    }

    @Override
    public UserModel updateUser(UpdateUserModel updateUserModel) {
        User user = userRepository.findById(updateUserModel.id())
            .orElseThrow(
                () -> new IllegalArgumentException("User not found: " + updateUserModel.id()));

        if (updateUserModel.name() != null) {
            user.setName(updateUserModel.name());
        }
        if (updateUserModel.about() != null) {
            user.setAbout(updateUserModel.about());
        }
        if (updateUserModel.location() != null) {
            user.setLocation(updateUserModel.location());
        }
        if (updateUserModel.categories() != null) {
            user.setCategories(new ArrayList<>(updateUserModel.categories()));
        }

        User saved = userRepository.save(user);
        return toModel(saved);
    }

    @Override
    public UserModel updateSkills(UpdateSkillsModel updateSkillsModel) {
        User user = userRepository.findById(updateSkillsModel.userId())
            .orElseThrow(() -> new IllegalArgumentException(
                "User not found: " + updateSkillsModel.userId()));

        if (updateSkillsModel.skills() != null) {
            user.setSkills(new ArrayList<>(updateSkillsModel.skills()));
        }

        User saved = userRepository.save(user);
        return toModel(saved);
    }

    @Override
    public UserModel updateVisibility(String userId, boolean isPublic) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        user.setProfilePublic(isPublic);
        User saved = userRepository.save(user);
        return toModel(saved);
    }

    @Override
    public PublicUserModel getPublicProfile(String userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        if (!user.isProfilePublic()) {
            throw new IllegalArgumentException("This profile is private.");
        }

        return new PublicUserModel(
            user.getId(),
            user.getName(),
            user.getAbout(),
            user.getLocation(),
            user.getCategories(),
            user.getSkills()
        );
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    private UserModel toModel(User user) {
        return new UserModel(
            user.getId(),
            user.getEmail(),
            user.getName(),
            user.getAbout(),
            user.getLocation(),
            user.getCategories(),
            user.getSkills(),
            user.isProfilePublic(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}
