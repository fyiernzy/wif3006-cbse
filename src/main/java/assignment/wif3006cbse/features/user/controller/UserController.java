package assignment.wif3006cbse.features.user.controller;

import assignment.wif3006cbse.features.user.dto.CreateUserModel;
import assignment.wif3006cbse.features.user.dto.UserModel;
import assignment.wif3006cbse.features.user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserModel createUser(@RequestBody @Valid CreateUserModel createUserModel) {
        return userService.createUser(createUserModel);
    }

    @GetMapping("/{id}")
    public UserModel findUserById(@PathVariable @NotNull String id) {
        return userService.findUserById(id);
    }

    @GetMapping("/bulk")
    public PagedModel<UserModel> findAllUsers(@PageableDefault Pageable pageable) {
        return new PagedModel<>(userService.findAllUsers(pageable));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable @NotNull String id) {
        userService.deleteUserById(id);
    }
}
