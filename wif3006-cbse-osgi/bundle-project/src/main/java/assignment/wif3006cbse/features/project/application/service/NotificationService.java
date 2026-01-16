package assignment.wif3006cbse.features.project.application.service;

import assignment.wif3006cbse.features.project.application.dto.notification.NotificationModel;
import assignment.wif3006cbse.features.project.domain.entity.NotificationType;

import java.util.List;

/**
 * Service interface for Notification operations.
 * Dependency Depth 2 - depends on NotificationDAO.
 */
public interface NotificationService {

    /**
     * Send a notification to a user.
     */
    NotificationModel sendNotification(String userId, String senderId, String message, NotificationType type);

    /**
     * Get all notifications for a user.
     */
    List<NotificationModel> getNotificationsByUserId(String userId);

    /**
     * Get unread notifications for a user.
     */
    List<NotificationModel> getUnreadNotificationsByUserId(String userId);

    /**
     * Mark a notification as read.
     */
    boolean markAsRead(String notificationId);

    /**
     * Mark all notifications as read for a user.
     */
    void markAllAsReadForUser(String userId);

    /**
     * Count unread notifications for a user.
     */
    long countUnreadNotifications(String userId);

    /**
     * Delete a notification.
     */
    boolean deleteNotification(String notificationId);
}
