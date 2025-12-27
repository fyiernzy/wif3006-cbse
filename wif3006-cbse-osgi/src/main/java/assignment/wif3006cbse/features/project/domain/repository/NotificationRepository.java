package assignment.wif3006cbse.features.project.domain.repository;

import assignment.wif3006cbse.features.project.domain.entity.Notification;
import assignment.wif3006cbse.features.project.domain.entity.NotificationType;
import assignment.wif3006cbse.shared.spi.CrudRepository;

import java.util.List;

/**
 * Repository interface for Notification entity (NotificationDAO).
 * Dependency Depth 1 - depends on Notification model.
 */
public interface NotificationRepository extends CrudRepository<Notification, String> {

    /**
     * Find all notifications for a specific user.
     */
    List<Notification> findByUserId(String userId);

    /**
     * Find unread notifications for a user.
     */
    List<Notification> findUnreadByUserId(String userId);

    /**
     * Find notifications by type.
     */
    List<Notification> findByType(NotificationType type);

    /**
     * Mark all notifications as read for a user.
     */
    void markAllAsReadForUser(String userId);

    /**
     * Count unread notifications for a user.
     */
    long countUnreadByUserId(String userId);
}
