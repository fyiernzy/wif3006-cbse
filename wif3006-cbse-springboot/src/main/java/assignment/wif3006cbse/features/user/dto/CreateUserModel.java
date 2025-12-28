package assignment.wif3006cbse.features.user.dto;

import java.util.List;
import jakarta.validation.constraints.NotBlank;
import assignment.wif3006cbse.features.user.domain.UserRole;

public record CreateUserModel(
        @NotBlank String username,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String password,
        @NotBlank String email,
        UserRole role,
        byte[] profilePic,
        String headline,
        String city,
        String state,
        List<String> categories,
        String university,
        List<String> skills,
        String about,
        String rating,
        List<String> favoriteProjects,
        List<String> takenProjects,
        List<String> applyingProjects,
        List<String> completedProjects,
        List<String> postedProjects,
        List<String> experienceIds,
        List<String> productIds) {
}
