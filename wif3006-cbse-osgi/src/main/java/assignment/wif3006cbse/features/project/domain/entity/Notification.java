package assignment.wif3006cbse.features.project.domain.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Notification entity representing a system notification.
 * Corresponds to the Notification model in the dependency table (Depth 0).
 */
public class Notification implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String userId; // Target user who receives the notification
    private String senderId; // User who triggered the notification
    private String message;
    private NotificationType type;
    private boolean read;
    private LocalDateTime createdAt;

    public Notification() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.read = false;
    }

    public Notification(String userId, String senderId, String message, NotificationType type) {
        this();
        this.userId = userId;
        this.senderId = senderId;
        this.message = message;
        this.type = type;
    }

    // Getters and Setters
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

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void markAsRead() {
        this.read = true;
    }
}
