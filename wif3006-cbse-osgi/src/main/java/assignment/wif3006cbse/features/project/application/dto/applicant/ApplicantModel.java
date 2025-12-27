package assignment.wif3006cbse.features.project.application.dto.applicant;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;

/**
 * DTO for applicant details response.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record ApplicantModel(
    String id,
    String projectId,
    String userId,
    String status,
    String coverLetter,
    LocalDateTime appliedAt,
    LocalDateTime updatedAt
) {}
