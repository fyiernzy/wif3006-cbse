package assignment.wif3006cbse.features.profile.application.dto.jobhistory;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record JobHistoryModel(
    String id,
    String userId,
    String projectId,
    String projectTitle,
    String role,
    String status,
    double rating,
    String review,
    String deliverables,
    LocalDateTime startedAt,
    LocalDateTime completedAt,
    LocalDateTime createdAt
) {

}
