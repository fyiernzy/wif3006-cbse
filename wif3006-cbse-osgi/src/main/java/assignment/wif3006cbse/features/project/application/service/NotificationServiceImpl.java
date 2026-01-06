package assignment.wif3006cbse.features.project.application.service;

import assignment.wif3006cbse.features.project.application.dto.notification.NotificationModel;
import assignment.wif3006cbse.features.project.domain.entity.Notification;
import assignment.wif3006cbse.features.project.domain.entity.NotificationType;
import assignment.wif3006cbse.features.project.domain.repository.NotificationRepository;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;

/**
 * Implementation of NotificationService.
 * Dependency Depth 2 - depends on NotificationRepository (NotificationDAO).
 */
@Component(service = NotificationService.class)
public class NotificationServiceImpl implements NotificationService {

    @Reference
    private NotificationRepository notificationRepository;

    @Override
    public NotificationModel sendNotification(String userId, String senderId, String message, NotificationType type) {
        Notification notification = new Notification(userId, senderId, message, type);
        Notification saved = notificationRepository.save(notification);
        return toNotificationModel(saved);
    }

    @Override
    public List<NotificationModel> getNotificationsByUserId(String userId) {
        return notificationRepository.findByUserId(userId).stream()
                .map(this::toNotificationModel)
                .toList();
    }

    @Override
    public List<NotificationModel> getUnreadNotificationsByUserId(String userId) {
        return notificationRepository.findUnreadByUserId(userId).stream()
                .map(this::toNotificationModel)
                .toList();
    }

    @Override
    public boolean markAsRead(String notificationId) {
        return notificationRepository.findById(notificationId)
                .map(notification -> {
                    notification.markAsRead();
                    notificationRepository.save(notification);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public void markAllAsReadForUser(String userId) {
        notificationRepository.markAllAsReadForUser(userId);
    }

    @Override
    public long countUnreadNotifications(String userId) {
        return notificationRepository.countUnreadByUserId(userId);
    }

    @Override
    public boolean deleteNotification(String notificationId) {
        notificationRepository.deleteById(notificationId);
        return true;
    }

    private NotificationModel toNotificationModel(Notification notification) {
        return new NotificationModel(
            notification.getId(),
            notification.getUserId(),
            notification.getSenderId(),
            notification.getMessage(),
            notification.getType() != null ? notification.getType().name() : null,
            notification.isRead(),
            notification.getCreatedAt()
        );
    }
}
