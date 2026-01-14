package assignment.wif3006cbse.features.community.web;

import assignment.wif3006cbse.features.community.application.dto.thread.CreateThreadModel;
import assignment.wif3006cbse.features.community.application.dto.thread.ThreadEntityModel;
import assignment.wif3006cbse.features.community.application.dto.thread.UpdateThreadModel;
import assignment.wif3006cbse.features.community.application.service.ThreadEntityService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component(service = {ThreadEntityController.class}, property = {
    "osgi.jaxrs.resource=true",
    "osgi.jaxrs.application.select=(osgi.jaxrs.name=main)",
})
@Path("/api/v1/threads")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ThreadEntityController {

    @Reference
    private ThreadEntityService threadService;

    @POST
    public Response createThread(CreateThreadModel createThreadModel) {
        ThreadEntityModel created = threadService.createThread(createThreadModel);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @Path("/post/{postId}")
    public Response getThreadsByPostId(@PathParam("postId") String postId) {
        List<ThreadEntityModel> threads = threadService.findThreadsByPostId(postId);
        return Response.ok(threads).build();
    }

    @PUT
    public Response updateThread(UpdateThreadModel updateThreadModel) {
        ThreadEntityModel updated = threadService.updateThread(updateThreadModel);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteThread(@PathParam("id") String id) {
        threadService.deleteThreadById(id);
        return Response.noContent().build();
    }
}
