package assignment.wif3006cbse.features.project.domain.repository;

import assignment.wif3006cbse.features.project.domain.entity.Notification;
import assignment.wif3006cbse.features.project.domain.entity.NotificationType;
import assignment.wif3006cbse.shared.FileBasedRepository;
import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * File-based implementation of NotificationRepository.
 * Corresponds to NotificationDAO (Depth 1).
 */
@Component(service = NotificationRepository.class)
public class NotificationRepositoryImpl extends FileBasedRepository<Notification, String> implements NotificationRepository {

    public NotificationRepositoryImpl() {
        super("notifications.json", Notification.class, Notification::getId);
    }

    @Override
    public List<Notification> findByUserId(String userId) {
        return findAll().stream()
                .filter(n -> userId.equals(n.getUserId()))
                .toList();
    }

    @Override
    public List<Notification> findUnreadByUserId(String userId) {
        return findAll().stream()
                .filter(n -> userId.equals(n.getUserId()) && !n.isRead())
                .toList();
    }

    @Override
    public List<Notification> findByType(NotificationType type) {
        return findAll().stream()
                .filter(n -> type.equals(n.getType()))
                .toList();
    }

    @Override
    public void markAllAsReadForUser(String userId) {
        List<Notification> notifications = findUnreadByUserId(userId);
        notifications.forEach(n -> {
            n.markAsRead();
            save(n);
        });
    }

    @Override
    public long countUnreadByUserId(String userId) {
        return findAll().stream()
                .filter(n -> userId.equals(n.getUserId()) && !n.isRead())
                .count();
    }
}
