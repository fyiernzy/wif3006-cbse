package assignment.wif3006cbse.features.profile.web;

import assignment.wif3006cbse.features.profile.application.dto.user.*;
import assignment.wif3006cbse.features.profile.application.service.UserService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Component(service = {UserController.class}, property = {
    "osgi.jaxrs.resource=true",
    "osgi.jaxrs.application.select=(osgi.jaxrs.name=main)",
})
@Path("/api/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Reference
    private UserService userService;

    // UC 03-01: Manage User Profile - Create User
    @POST
    public Response createUser(CreateUserModel createUserModel) {
        try {
            UserModel user = userService.createUser(createUserModel);
            return Response.status(201).entity(user).build();
        } catch (Exception e) {
            return Response.status(400)
                .entity(Map.of("error", "Failed to create user: " + e.getMessage()))
                .build();
        }
    }

    // UC 03-01: Manage User Profile - Get User Profile
    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") String id) {
        try {
            UserModel user = userService.findUserById(id);
            return Response.ok(user).build();
        } catch (IllegalArgumentException e) {
            return Response.status(404)
                .entity(Map.of("error", e.getMessage()))
                .build();
        }
    }
    // Get all users
    @GET
    @Path("/all")
    public Response getAllUsers() {
        try {
            List<UserModel> users = userService.getAllUsers();
            return Response.ok(users).build();
        } catch (IllegalArgumentException e) {
            return Response.status(404)
                .entity(Map.of("error", e.getMessage()))
                .build();
        }
    }

    // UC 03-01: Manage User Profile - Update User Profile
    @PUT
    public Response updateUser(UpdateUserModel updateUserModel) {
        try {
            UserModel user = userService.updateUser(updateUserModel);
            return Response.ok(user)
                .entity(Map.of("message", "Profile updated successfully", "user", user))
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

    // UC 03-02: Manage Skills
    @PUT
    @Path("/skills")
    public Response updateSkills(UpdateSkillsModel updateSkillsModel) {
        try {
            UserModel user = userService.updateSkills(updateSkillsModel);
            return Response.ok(user)
                .entity(Map.of("message", "Skills updated successfully", "user", user))
                .build();
        } catch (IllegalArgumentException e) {
            return Response.status(404)
                .entity(Map.of("error", e.getMessage()))
                .build();
        }
    }

    // UC 03-01: Update Visibility Settings
    @PUT
    @Path("/{id}/visibility")
    public Response updateVisibility(
        @PathParam("id") String userId,
        @QueryParam("isPublic") @DefaultValue("true") boolean isPublic
    ) {
        try {
            UserModel user = userService.updateVisibility(userId, isPublic);
            return Response.ok(user)
                .entity(Map.of("message", "Visibility updated successfully", "user", user))
                .build();
        } catch (IllegalArgumentException e) {
            return Response.status(404)
                .entity(Map.of("error", e.getMessage()))
                .build();
        }
    }

    // UC 03-05: View Public Profile
    @GET
    @Path("/{id}/public")
    public Response getPublicProfile(@PathParam("id") String userId) {
        try {
            PublicUserModel publicProfile = userService.getPublicProfile(userId);
            return Response.ok(publicProfile).build();
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("private")) {
                return Response.status(403)
                    .entity(Map.of("message", "This profile is private."))
                    .build();
            }
            return Response.status(404)
                .entity(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        try {
            userService.deleteUser(id);
            return Response.ok()
                .entity(Map.of("message", "User deleted successfully"))
                .build();
        } catch (Exception e) {
            return Response.status(500)
                .entity(Map.of("error", "Failed to delete user: " + e.getMessage()))
                .build();
        }
    }
}
