package assignment.wif3006cbse.features.project.dto.project;

import jakarta.validation.constraints.NotBlank;

public record ProjectUserRequest(
        @NotBlank String userId,
        @NotBlank String projectId) {
}
