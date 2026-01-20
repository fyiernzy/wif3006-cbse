package assignment.wif3006cbse.features.project.domain.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Review entity representing a project review after completion.
 * Corresponds to the Review concept managed by ReviewDAO (Depth 1).
 * Contains satisfaction rating, project rating, collaborator rating, and feedback.
 */
public class Review implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String projectId;
    private String reviewerId; // User ID of the person giving the review (recruiter)
    private String revieweeId; // User ID of the person being reviewed (freelancer)
    
    // Ratings (1-5 scale)
    private int satisfactionRating;
    private int projectRating;
    private int collaboratorRating;
    
    // Feedback
    private String projectFeedback;
    private String collaboratorFeedback;
    
    private LocalDateTime createdAt;

    public Review() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    public Review(String projectId, String reviewerId, String revieweeId,
                  int satisfactionRating, int projectRating, int collaboratorRating,
                  String projectFeedback, String collaboratorFeedback) {
        this();
        this.projectId = projectId;
        this.reviewerId = reviewerId;
        this.revieweeId = revieweeId;
        this.satisfactionRating = satisfactionRating;
        this.projectRating = projectRating;
        this.collaboratorRating = collaboratorRating;
        this.projectFeedback = projectFeedback;
        this.collaboratorFeedback = collaboratorFeedback;
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

    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getRevieweeId() {
        return revieweeId;
    }

    public void setRevieweeId(String revieweeId) {
        this.revieweeId = revieweeId;
    }

    public int getSatisfactionRating() {
        return satisfactionRating;
    }

    public void setSatisfactionRating(int satisfactionRating) {
        this.satisfactionRating = Math.max(1, Math.min(5, satisfactionRating));
    }

    public int getProjectRating() {
        return projectRating;
    }

    public void setProjectRating(int projectRating) {
        this.projectRating = Math.max(1, Math.min(5, projectRating));
    }

    public int getCollaboratorRating() {
        return collaboratorRating;
    }

    public void setCollaboratorRating(int collaboratorRating) {
        this.collaboratorRating = Math.max(1, Math.min(5, collaboratorRating));
    }

    public String getProjectFeedback() {
        return projectFeedback;
    }

    public void setProjectFeedback(String projectFeedback) {
        this.projectFeedback = projectFeedback;
    }

    public String getCollaboratorFeedback() {
        return collaboratorFeedback;
    }

    public void setCollaboratorFeedback(String collaboratorFeedback) {
        this.collaboratorFeedback = collaboratorFeedback;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Calculate the average rating from all three rating categories.
     */
    public double getAverageRating() {
        return (satisfactionRating + projectRating + collaboratorRating) / 3.0;
    }
}
