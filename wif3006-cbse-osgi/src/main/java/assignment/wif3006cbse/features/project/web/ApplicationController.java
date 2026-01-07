package assignment.wif3006cbse.features.project.web;

import assignment.wif3006cbse.features.project.application.dto.applicant.ApplicantModel;
import assignment.wif3006cbse.features.project.application.dto.applicant.CreateApplicantModel;
import assignment.wif3006cbse.features.project.application.service.ApplicationService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * REST Controller for Application/Applicant operations.
 * Dependency Depth 4 - depends on ApplicationService.
 * Provides endpoints for job application workflow.
 */
@Component(
    service = ApplicationController.class,
    property = {
        "osgi.jaxrs.resource=true",
        "osgi.jaxrs.application.select=(osgi.jaxrs.name=main)",
    }
)
@Path("/applications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApplicationController {

    @Reference
    private ApplicationService applicationService;

    /**
     * Apply to a project.
     */
    @POST
    public Response applyToProject(CreateApplicantModel model) {
        try {
            ApplicantModel created = applicationService.applyToProject(model);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        } catch (IllegalStateException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }

    /**
     * Get application by ID.
     */
    @GET
    @Path("/{applicationId}")
    public Response getApplicationById(@PathParam("applicationId") String applicationId) {
        return applicationService.getApplicationById(applicationId)
                .map(application -> Response.ok(application).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Application not found"))
                        .build());
    }

    /**
     * Get all applications for a project.
     */
    @GET
    @Path("/project/{projectId}")
    public Response getApplicationsByProjectId(@PathParam("projectId") String projectId) {
        List<ApplicantModel> applications = applicationService.getApplicationsByProjectId(projectId);
        return Response.ok(applications).build();
    }

    /**
     * Get all applications by a user.
     */
    @GET
    @Path("/user/{userId}")
    public Response getApplicationsByUserId(@PathParam("userId") String userId) {
        List<ApplicantModel> applications = applicationService.getApplicationsByUserId(userId);
        return Response.ok(applications).build();
    }

    /**
     * Check if user has applied to a project.
     */
    @GET
    @Path("/check/{projectId}/{userId}")
    public Response hasUserApplied(@PathParam("projectId") String projectId, @PathParam("userId") String userId) {
        boolean hasApplied = applicationService.hasUserApplied(projectId, userId);
        return Response.ok(new HasAppliedResponse(hasApplied)).build();
    }

    /**
     * Accept an application.
     */
    @POST
    @Path("/{applicationId}/accept")
    public Response acceptApplication(@PathParam("applicationId") String applicationId) {
        return applicationService.acceptApplication(applicationId)
                .map(application -> Response.ok(application).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Application not found"))
                        .build());
    }

    /**
     * Reject an application.
     */
    @POST
    @Path("/{applicationId}/reject")
    public Response rejectApplication(@PathParam("applicationId") String applicationId) {
        return applicationService.rejectApplication(applicationId)
                .map(application -> Response.ok(application).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Application not found"))
                        .build());
    }

    /**
     * Withdraw an application.
     */
    @POST
    @Path("/{applicationId}/withdraw")
    public Response withdrawApplication(@PathParam("applicationId") String applicationId) {
        return applicationService.withdrawApplication(applicationId)
                .map(application -> Response.ok(application).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Application not found"))
                        .build());
    }

    /**
     * Delete an application.
     */
    @DELETE
    @Path("/{applicationId}")
    public Response deleteApplication(@PathParam("applicationId") String applicationId) {
        boolean deleted = applicationService.deleteApplication(applicationId);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse("Application not found"))
                .build();
    }

    /**
     * Simple error response wrapper.
     */
    public record ErrorResponse(String message) {}

    /**
     * Response for has applied check.
     */
    public record HasAppliedResponse(boolean hasApplied) {}
}
