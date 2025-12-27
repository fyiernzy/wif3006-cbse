package assignment.wif3006cbse.features.project.domain.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Applicant entity representing a freelancer's application to a project.
 * Links Project and User (applicant) with application status.
 * Corresponds to the Applicant concept managed by ApplicantDAO (Depth 1).
 */
public class Applicant implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String projectId;
    private String userId; // The freelancer who is applying
    private ApplicantStatus status;
    private String coverLetter;
    private LocalDateTime appliedAt;
    private LocalDateTime updatedAt;

    public Applicant() {
        this.id = UUID.randomUUID().toString();
        this.appliedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = ApplicantStatus.PENDING;
    }

    public Applicant(String projectId, String userId, String coverLetter) {
        this();
        this.projectId = projectId;
        this.userId = userId;
        this.coverLetter = coverLetter;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ApplicantStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicantStatus status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }
}
