package assignment.wif3006cbse.features.profile.dto.user;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UpdateSkillsModel(
    @NotBlank String id,
    List<String> skills
) {

}
