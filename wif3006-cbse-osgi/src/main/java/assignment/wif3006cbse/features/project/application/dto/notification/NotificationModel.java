package assignment.wif3006cbse.features.project.application.dto.notification;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;

/**
 * DTO for notification details response.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record NotificationModel(
    String id,
    String userId,
    String senderId,
    String message,
    String type,
    boolean read,
    LocalDateTime createdAt
) {}
