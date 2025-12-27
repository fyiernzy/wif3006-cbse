package assignment.wif3006cbse.features.project.application.dto.review;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;

/**
 * DTO for review details response.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record ReviewModel(
    String id,
    String projectId,
    String reviewerId,
    String revieweeId,
    int satisfactionRating,
    int projectRating,
    int collaboratorRating,
    String projectFeedback,
    String collaboratorFeedback,
    double averageRating,
    LocalDateTime createdAt
) {}
