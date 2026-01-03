package assignment.wif3006cbse.features.profile.controller;

import assignment.wif3006cbse.features.profile.dto.user.*;
import assignment.wif3006cbse.features.profile.service.ProfileUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile/users")
public class ProfileUserController {

    private final ProfileUserService userService;

    @PostMapping
    public UserModel createUser(@RequestBody @Valid CreateUserModel createUserModel) {
        return userService.createUser(createUserModel);
    }

    @GetMapping("/{id}")
    public UserModel findUserById(@PathVariable @NotBlank String id) {
        return userService.findUserById(id);
    }

    @GetMapping("/email/{email}")
    public UserModel findUserByEmail(@PathVariable @NotBlank String email) {
        return userService.findUserByEmail(email);
    }

    @PutMapping
    public UserModel updateUser(@RequestBody @Valid UpdateUserModel updateUserModel) {
        return userService.updateUser(updateUserModel);
    }

    @PutMapping("/skills")
    public UserModel updateSkills(@RequestBody @Valid UpdateSkillsModel updateSkillsModel) {
        return userService.updateSkills(updateSkillsModel);
    }

    @PutMapping("/{id}/visibility")
    public UserModel updateVisibility(@PathVariable @NotBlank String id, 
                                      @RequestParam @NotNull Boolean isPublic) {
        return userService.updateVisibility(id, isPublic);
    }

    @GetMapping("/{id}/public")
    public PublicUserModel getPublicProfile(@PathVariable @NotBlank String id) {
        return userService.getPublicProfile(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable @NotBlank String id) {
        userService.deleteUserById(id);
    }
}
