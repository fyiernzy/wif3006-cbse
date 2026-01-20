package assignment.wif3006cbse.features.project.domain.entity;

/**
 * Enum representing the status of a project applicant.
 */
public enum ApplicantStatus {
    PENDING,    // Application submitted, waiting for review
    ACCEPTED,   // Application accepted, freelancer is assigned to project
    REJECTED,   // Application rejected by the recruiter
    WITHDRAWN   // Application withdrawn by the applicant
}
