package assignment.wif3006cbse.features.profile.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreateUserModel(
    @NotBlank @Email String email,
    @NotBlank String name,
    String about,
    String location,
    List<String> categories,
    List<String> skills,
    Boolean isProfilePublic
) {

}
