package assignment.wif3006cbse.features.project.web;

import assignment.wif3006cbse.features.project.application.dto.deliverable.CreateUploadedFileModel;
import assignment.wif3006cbse.features.project.application.dto.deliverable.UploadedFileModel;
import assignment.wif3006cbse.features.project.application.service.DeliverableService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * REST Controller for Deliverable/File operations.
 * Dependency Depth 4 - depends on DeliverableService.
 * Provides endpoints for file upload/download and deliverable management.
 */
@Component(
    service = DeliverableController.class,
    property = {
        "osgi.jaxrs.resource=true",
        "osgi.jaxrs.application.select=(osgi.jaxrs.name=main)",
    }
)
@Path("/deliverables")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeliverableController {

    @Reference
    private DeliverableService deliverableService;

    /**
     * Upload a deliverable file.
     */
    @POST
    public Response uploadFile(CreateUploadedFileModel model) {
        try {
            UploadedFileModel uploaded = deliverableService.uploadFile(model);
            return Response.status(Response.Status.CREATED).entity(uploaded).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        } catch (IllegalStateException e) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }

    /**
     * Get file by ID.
     */
    @GET
    @Path("/{fileId}")
    public Response getFileById(@PathParam("fileId") String fileId) {
        return deliverableService.getFileById(fileId)
                .map(file -> Response.ok(file).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("File not found"))
                        .build());
    }

    /**
     * Get all files for a project.
     */
    @GET
    @Path("/project/{projectId}")
    public Response getFilesByProjectId(@PathParam("projectId") String projectId) {
        List<UploadedFileModel> files = deliverableService.getFilesByProjectId(projectId);
        return Response.ok(files).build();
    }

    /**
     * Get all files uploaded by a user.
     */
    @GET
    @Path("/user/{userId}")
    public Response getFilesByUploadedBy(@PathParam("userId") String userId) {
        List<UploadedFileModel> files = deliverableService.getFilesByUploadedBy(userId);
        return Response.ok(files).build();
    }

    /**
     * Delete a file.
     */
    @DELETE
    @Path("/{fileId}")
    public Response deleteFile(@PathParam("fileId") String fileId) {
        boolean deleted = deliverableService.deleteFile(fileId);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse("File not found"))
                .build();
    }

    /**
     * Submit deliverables for review.
     */
    @POST
    @Path("/project/{projectId}/submit")
    public Response submitForReview(@PathParam("projectId") String projectId, SubmitRequest request) {
        try {
            deliverableService.submitDeliverablesForReview(projectId, request.submitterId());
            return Response.ok(new SuccessResponse("Deliverables submitted for review")).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }

    /**
     * Accept deliverables.
     */
    @POST
    @Path("/project/{projectId}/accept")
    public Response acceptDeliverables(@PathParam("projectId") String projectId, ReviewerRequest request) {
        try {
            deliverableService.acceptDeliverables(projectId, request.reviewerId());
            return Response.ok(new SuccessResponse("Deliverables accepted")).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }

    /**
     * Reject deliverables.
     */
    @POST
    @Path("/project/{projectId}/reject")
    public Response rejectDeliverables(@PathParam("projectId") String projectId, RejectRequest request) {
        try {
            deliverableService.rejectDeliverables(projectId, request.reviewerId(), request.reason());
            return Response.ok(new SuccessResponse("Deliverables rejected")).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }

    /**
     * Request DTOs.
     */
    public record SubmitRequest(String submitterId) {}
    public record ReviewerRequest(String reviewerId) {}
    public record RejectRequest(String reviewerId, String reason) {}

    /**
     * Response wrappers.
     */
    public record ErrorResponse(String message) {}
    public record SuccessResponse(String message) {}
}
