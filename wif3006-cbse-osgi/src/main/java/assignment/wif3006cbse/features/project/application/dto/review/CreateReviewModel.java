package assignment.wif3006cbse.features.project.application.dto.review;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * DTO for creating a new review.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record CreateReviewModel(
    String projectId,
    String reviewerId,
    String revieweeId,
    int satisfactionRating,
    int projectRating,
    int collaboratorRating,
    String projectFeedback,
    String collaboratorFeedback
) {}
