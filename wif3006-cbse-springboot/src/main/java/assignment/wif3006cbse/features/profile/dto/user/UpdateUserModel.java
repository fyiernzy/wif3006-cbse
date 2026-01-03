package assignment.wif3006cbse.features.profile.dto.user;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UpdateUserModel(
    @NotBlank String id,
    String name,
    String about,
    String location,
    List<String> categories
) {

}
