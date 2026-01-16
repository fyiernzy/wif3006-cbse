package assignment.wif3006cbse.features.profile.domain.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class JobHistory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String userId;
    private String projectId;
    private String projectTitle;
    private String role;
    private String status; // COMPLETED, ONGOING, CANCELLED
    private double rating;
    private String review;
    private String deliverables;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private LocalDateTime createdAt;

    public JobHistory() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.status = "ONGOING";
    }

    public JobHistory(String userId, String projectId, String projectTitle, String role) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.role = role;
        this.startedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
        this.status = "ONGOING";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getDeliverables() {
        return deliverables;
    }

    public void setDeliverables(String deliverables) {
        this.deliverables = deliverables;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
