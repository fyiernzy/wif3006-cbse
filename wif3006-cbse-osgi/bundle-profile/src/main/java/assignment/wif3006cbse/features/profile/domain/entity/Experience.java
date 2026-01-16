package assignment.wif3006cbse.features.profile.domain.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Experience implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String userId;
    private String title;
    private String company;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isCurrent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Experience() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isCurrent = false;
    }

    public Experience(String userId, String title, String company, LocalDate startDate) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.title = title;
        this.company = company;
        this.startDate = startDate;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isCurrent = false;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
        this.updatedAt = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
