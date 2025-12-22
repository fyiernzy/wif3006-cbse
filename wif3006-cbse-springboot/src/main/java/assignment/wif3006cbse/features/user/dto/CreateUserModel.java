package assignment.wif3006cbse.features.user.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserModel(
    @NotBlank String username,
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotBlank String password
) {
}
