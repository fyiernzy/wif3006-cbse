package assignment.wif3006cbse.features.profile.web;

import assignment.wif3006cbse.features.profile.application.dto.experience.CreateExperienceModel;
import assignment.wif3006cbse.features.profile.application.dto.experience.ExperienceModel;
import assignment.wif3006cbse.features.profile.application.dto.experience.UpdateExperienceModel;
import assignment.wif3006cbse.features.profile.application.service.ExperienceService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Component(service = {ExperienceController.class}, property = {
    "osgi.jaxrs.resource=true",
    "osgi.jaxrs.application.select=(osgi.jaxrs.name=main)",
})
@Path("/api/v1/experiences")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExperienceController {

    @Reference
    private ExperienceService experienceService;

    // UC 03-03: Manage Experience - Create Experience
    @POST
    public Response createExperience(CreateExperienceModel createExperienceModel) {
        try {
            ExperienceModel experience = experienceService.createExperience(createExperienceModel);
            return Response.status(201).entity(experience).build();
        } catch (Exception e) {
            return Response.status(400)
                .entity(Map.of("error", "Failed to create experience: " + e.getMessage()))
                .build();
        }
    }

    // UC 03-03: Manage Experience - Get Experience by ID
    @GET
    @Path("/{id}")
    public Response getExperienceById(@PathParam("id") String id) {
        try {
            ExperienceModel experience = experienceService.findExperienceById(id);
            return Response.ok(experience).build();
        } catch (IllegalArgumentException e) {
            return Response.status(404)
                .entity(Map.of("error", e.getMessage()))
                .build();
        }
    }

    // UC 03-03: Manage Experience - Get All Experiences by User
    @GET
    @Path("/user/{userId}")
    public Response getExperiencesByUserId(@PathParam("userId") String userId) {
        try {
            List<ExperienceModel> experiences = experienceService.findAllByUserId(userId);
            return Response.ok(experiences).build();
        } catch (Exception e) {
            return Response.status(500)
                .entity(Map.of("error", "Failed to retrieve experiences: " + e.getMessage()))
                .build();
        }
    }

    // UC 03-03: Manage Experience - Update Experience
    @PUT
    public Response updateExperience(UpdateExperienceModel updateExperienceModel) {
        try {
            ExperienceModel experience = experienceService.updateExperience(updateExperienceModel);
            return Response.ok(experience)
                .entity(Map.of("message", "Experience updated successfully", "experience",
                    experience))
                .build();
        } catch (IllegalArgumentException e) {
            return Response.status(404)
                .entity(Map.of("error", e.getMessage()))
                .build();
        } catch (Exception e) {
            return Response.status(400)
                .entity(Map.of("error", "Invalid data: " + e.getMessage()))
                .build();
        }
    }

    // UC 03-03: Manage Experience - Delete Experience (with confirmation)
    @DELETE
    @Path("/{id}")
    public Response deleteExperience(@PathParam("id") String id) {
        try {
            experienceService.deleteExperience(id);
            return Response.ok()
                .entity(Map.of("message", "Experience deleted successfully"))
                .build();
        } catch (IllegalArgumentException e) {
            return Response.status(404)
                .entity(Map.of("error", e.getMessage()))
                .build();
        } catch (Exception e) {
            return Response.status(500)
                .entity(Map.of("error", "Failed to delete experience: " + e.getMessage()))
                .build();
        }
    }
}
