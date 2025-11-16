package assignment.wif3006cbse.features.user.service;

import assignment.wif3006cbse.features.user.domain.User;
import assignment.wif3006cbse.features.user.domain.UserRepository;
import assignment.wif3006cbse.features.user.dto.CreateUserModel;
import assignment.wif3006cbse.features.user.dto.UserModel;
import assignment.wif3006cbse.features.user.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Transactional
    public UserModel createUser(@Valid CreateUserModel createUserModel) {
        User user = userMapper.toEntity(createUserModel);
        return userMapper.toModel(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserModel findUserById(@NotNull String id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));
        return userMapper.toModel(user);
    }

    @Transactional(readOnly = true)
    public Page<UserModel> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toModel);
    }

    @Transactional
    public void deleteUserById(@NotNull String id) {
        userRepository.deleteById(id);
    }
}
