package assignment.wif3006cbse.features.profile.service;

import assignment.wif3006cbse.features.profile.domain.entity.User;
import assignment.wif3006cbse.features.profile.domain.repository.ProfileUserRepository;
import assignment.wif3006cbse.features.profile.dto.user.*;
import assignment.wif3006cbse.features.profile.mapper.ProfileUserMapper;
import assignment.wif3006cbse.utils.Asserts;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class ProfileUserService {

    private final ProfileUserMapper userMapper;
    private final ProfileUserRepository userRepository;

    @Transactional
    public UserModel createUser(@Valid CreateUserModel createUserModel) {
        Asserts.state(!userRepository.existsByEmail(createUserModel.email()), 
            "Email already exists");
        User user = userMapper.toEntity(createUserModel);
        if (createUserModel.isProfilePublic() != null) {
            user.setProfilePublic(createUserModel.isProfilePublic());
        }
        return userMapper.toModel(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserModel findUserById(@NotBlank String id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));
        return userMapper.toModel(user);
    }

    @Transactional(readOnly = true)
    public UserModel findUserByEmail(@NotBlank String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));
        return userMapper.toModel(user);
    }

    @Transactional
    public UserModel updateUser(@Valid UpdateUserModel updateUserModel) {
        User user = userRepository.findById(updateUserModel.id())
            .orElseThrow(() -> new EntityNotFoundException("User not found."));
        userMapper.updateEntityFromUpdateModel(user, updateUserModel);
        return userMapper.toModel(userRepository.save(user));
    }

    @Transactional
    public UserModel updateSkills(@Valid UpdateSkillsModel updateSkillsModel) {
        User user = userRepository.findById(updateSkillsModel.id())
            .orElseThrow(() -> new EntityNotFoundException("User not found."));
        userMapper.updateSkillsFromModel(user, updateSkillsModel);
        return userMapper.toModel(userRepository.save(user));
    }

    @Transactional
    public UserModel updateVisibility(@NotBlank String id, @NotNull Boolean isPublic) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));
        user.setProfilePublic(isPublic);
        return userMapper.toModel(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public PublicUserModel getPublicProfile(@NotBlank String userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));
        Asserts.state(user.isProfilePublic(), "This profile is private.");
        return userMapper.toPublicModel(user);
    }

    @Transactional
    public void deleteUserById(@NotBlank String id) {
        userRepository.deleteById(id);
    }
}
