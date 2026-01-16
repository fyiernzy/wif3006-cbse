package assignment.wif3006cbse.features.project.domain.entity;

/**
 * Enum representing different types of notifications.
 */
public enum NotificationType {
    APPLICATION_RECEIVED,    // New application received for a project
    APPLICATION_ACCEPTED,    // Application was accepted
    APPLICATION_REJECTED,    // Application was rejected
    FILE_UPLOADED,          // Freelancer uploaded deliverable files
    FILE_ACCEPTED,          // Recruiter accepted the deliverables
    FILE_REJECTED,          // Recruiter rejected the deliverables
    PROJECT_COMPLETED,      // Project marked as completed
    REVIEW_RECEIVED         // Review was submitted
}
