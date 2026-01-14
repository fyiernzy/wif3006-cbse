package assignment.wif3006cbse.features.community.web;

import assignment.wif3006cbse.features.community.application.dto.thread.CreateThreadModel;
import assignment.wif3006cbse.features.community.application.dto.thread.ThreadModel;
import assignment.wif3006cbse.features.community.application.dto.thread.UpdateThreadModel;
import assignment.wif3006cbse.features.community.application.service.ThreadService;
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
    private ThreadService threadService;

    @POST
    public Response createThread(CreateThreadModel createThreadModel) {
        ThreadModel created = threadService.createThread(createThreadModel);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @Path("/{id}")
    public Response getThread(@PathParam("id") String id) {
        ThreadModel thread = threadService.findThreadById(id);
        return Response.ok(thread).build();
    }

    @GET
    public Response getAllThreads() {
        List<ThreadModel> threads = threadService.findAllThreads();
        return Response.ok(threads).build();
    }

    @GET
    @Path("/post/{postId}")
    public Response getThreadsByPostId(@PathParam("postId") String postId) {
        List<ThreadModel> threads = threadService.findThreadsByPostId(postId);
        return Response.ok(threads).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateThread(@PathParam("id") String id,
                                 UpdateThreadModel updateThreadModel) {
        // Ensure ID matches or handle separately. Here assuming Model contains correct
        // data.
        if (!id.equals(updateThreadModel.id())) {
            throw new IllegalArgumentException("ID mismatch");
        }
        ThreadModel updated = threadService.updateThread(updateThreadModel);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteThread(@PathParam("id") String id) {
        threadService.deleteThreadById(id);
        return Response.noContent().build();
    }
}
