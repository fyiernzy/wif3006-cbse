package assignment.wif3006cbse.features.project.application.dto.project;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record ProjectUserRequest(
        String userId,
        String projectId) {
}
