package assignment.wif3006cbse.features.project.web;

import assignment.wif3006cbse.features.project.application.dto.notification.NotificationModel;
import assignment.wif3006cbse.features.project.application.service.NotificationService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * REST Controller for Notification operations.
 * Dependency Depth 4 - depends on NotificationService.
 * Provides endpoints for user notifications.
 */
@Component(
    service = NotificationController.class,
    property = {
        "osgi.jaxrs.resource=true",
        "osgi.jaxrs.application.select=(osgi.jaxrs.name=project)"
    }
)
@Path("/notifications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotificationController {

    @Reference
    private NotificationService notificationService;

    /**
     * Get all notifications for a user.
     */
    @GET
    @Path("/user/{userId}")
    public Response getNotificationsByUserId(@PathParam("userId") String userId) {
        List<NotificationModel> notifications = notificationService.getNotificationsByUserId(userId);
        return Response.ok(notifications).build();
    }

    /**
     * Get unread notifications for a user.
     */
    @GET
    @Path("/user/{userId}/unread")
    public Response getUnreadNotificationsByUserId(@PathParam("userId") String userId) {
        List<NotificationModel> notifications = notificationService.getUnreadNotificationsByUserId(userId);
        return Response.ok(notifications).build();
    }

    /**
     * Count unread notifications for a user.
     */
    @GET
    @Path("/user/{userId}/unread/count")
    public Response countUnreadNotifications(@PathParam("userId") String userId) {
        long count = notificationService.countUnreadNotifications(userId);
        return Response.ok(new CountResponse(count)).build();
    }

    /**
     * Mark a notification as read.
     */
    @POST
    @Path("/{notificationId}/read")
    public Response markAsRead(@PathParam("notificationId") String notificationId) {
        boolean marked = notificationService.markAsRead(notificationId);
        if (marked) {
            return Response.ok(new SuccessResponse("Notification marked as read")).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse("Notification not found"))
                .build();
    }

    /**
     * Mark all notifications as read for a user.
     */
    @POST
    @Path("/user/{userId}/read-all")
    public Response markAllAsReadForUser(@PathParam("userId") String userId) {
        notificationService.markAllAsReadForUser(userId);
        return Response.ok(new SuccessResponse("All notifications marked as read")).build();
    }

    /**
     * Delete a notification.
     */
    @DELETE
    @Path("/{notificationId}")
    public Response deleteNotification(@PathParam("notificationId") String notificationId) {
        boolean deleted = notificationService.deleteNotification(notificationId);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse("Notification not found"))
                .build();
    }

    /**
     * Response wrappers.
     */
    public record ErrorResponse(String message) {}
    public record SuccessResponse(String message) {}
    public record CountResponse(long count) {}
}
