package assignment.wif3006cbse.features.project.web;

import assignment.wif3006cbse.features.project.application.dto.project.CreateProjectModel;
import assignment.wif3006cbse.features.project.application.dto.project.ProjectListModel;
import assignment.wif3006cbse.features.project.application.dto.project.ProjectModel;
import assignment.wif3006cbse.features.project.application.dto.project.UpdateProjectModel;
import assignment.wif3006cbse.features.project.application.service.ProjectService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * REST Controller for Project operations.
 * Dependency Depth 4 - depends on ProjectService.
 * Provides endpoints for CRUD operations on projects.
 */
@Component(
    service = ProjectController.class,
    property = {
        "osgi.jaxrs.resource=true",
        "osgi.jaxrs.application.select=(osgi.jaxrs.name=project)"
    }
)
@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectController {

    @Reference
    private ProjectService projectService;

    /**
     * Create a new project.
     */
    @POST
    public Response createProject(CreateProjectModel model) {
        try {
            ProjectModel created = projectService.createProject(model);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }

    /**
     * Get all projects.
     */
    @GET
    public Response getAllProjects() {
        List<ProjectListModel> projects = projectService.getAllProjects();
        return Response.ok(projects).build();
    }

    /**
     * Get available projects (not yet taken).
     */
    @GET
    @Path("/available")
    public Response getAvailableProjects() {
        List<ProjectListModel> projects = projectService.getAvailableProjects();
        return Response.ok(projects).build();
    }

    /**
     * Get project by ID.
     */
    @GET
    @Path("/{projectId}")
    public Response getProjectById(@PathParam("projectId") String projectId) {
        return projectService.getProjectById(projectId)
                .map(project -> Response.ok(project).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Project not found"))
                        .build());
    }

    /**
     * Get projects by poster/recruiter.
     */
    @GET
    @Path("/posted-by/{userId}")
    public Response getProjectsByPostedBy(@PathParam("userId") String userId) {
        List<ProjectListModel> projects = projectService.getProjectsByPostedBy(userId);
        return Response.ok(projects).build();
    }

    /**
     * Get projects assigned to a freelancer.
     */
    @GET
    @Path("/service-provider/{userId}")
    public Response getProjectsByServiceProvider(@PathParam("userId") String userId) {
        List<ProjectListModel> projects = projectService.getProjectsByServiceProvider(userId);
        return Response.ok(projects).build();
    }

    /**
     * Get projects by category.
     */
    @GET
    @Path("/category/{category}")
    public Response getProjectsByCategory(@PathParam("category") String category) {
        List<ProjectListModel> projects = projectService.getProjectsByCategory(category);
        return Response.ok(projects).build();
    }

    /**
     * Filter projects.
     */
    @POST
    @Path("/filter")
    public Response filterProjects(List<String> filters) {
        List<ProjectListModel> projects = projectService.getProjectsByFilters(filters);
        return Response.ok(projects).build();
    }

    /**
     * Update a project.
     */
    @PUT
    @Path("/{projectId}")
    public Response updateProject(@PathParam("projectId") String projectId, UpdateProjectModel model) {
        return projectService.updateProject(projectId, model)
                .map(project -> Response.ok(project).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Project not found"))
                        .build());
    }

    /**
     * Delete a project.
     */
    @DELETE
    @Path("/{projectId}")
    public Response deleteProject(@PathParam("projectId") String projectId) {
        boolean deleted = projectService.deleteProject(projectId);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse("Project not found"))
                .build();
    }

    /**
     * Complete a project.
     */
    @POST
    @Path("/{projectId}/complete")
    public Response completeProject(@PathParam("projectId") String projectId) {
        return projectService.completeProject(projectId)
                .map(project -> Response.ok(project).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Project not found"))
                        .build());
    }

    /**
     * Accept deliverables for a project.
     */
    @POST
    @Path("/{projectId}/accept-deliverables")
    public Response acceptDeliverables(@PathParam("projectId") String projectId) {
        return projectService.acceptDeliverables(projectId)
                .map(project -> Response.ok(project).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Project not found"))
                        .build());
    }

    /**
     * Simple error response wrapper.
     */
    public record ErrorResponse(String message) {}
}
