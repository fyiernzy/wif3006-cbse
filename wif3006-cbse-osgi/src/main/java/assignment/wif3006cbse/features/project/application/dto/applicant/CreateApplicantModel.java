package assignment.wif3006cbse.features.project.application.dto.applicant;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * DTO for creating a new application/applicant.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record CreateApplicantModel(
    String projectId,
    String userId,
    String coverLetter
) {}
