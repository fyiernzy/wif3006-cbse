package assignment.wif3006cbse.features.community.web;

import assignment.wif3006cbse.features.community.application.dto.thread.CreateThreadModel;
import assignment.wif3006cbse.features.community.application.dto.thread.ThreadModel;
import assignment.wif3006cbse.features.community.application.dto.thread.UpdateThreadModel;
import assignment.wif3006cbse.features.community.application.service.ThreadService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component(service = {ThreadController.class}, property = {
    "osgi.jaxrs.resource=true",
    "osgi.jaxrs.application.select=(osgi.jaxrs.name=main)",
})
@Path("/api/v1/threads")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ThreadController {

    @Reference
    private ThreadService threadService;

    @POST
    public ThreadModel createThread(CreateThreadModel createThreadModel) {
        return threadService.createThread(createThreadModel);
    }

    @GET
    @Path("/{id}")
    public ThreadModel getThread(@PathParam("id") String id) {
        return threadService.findThreadById(id);
    }

    @GET
    public List<ThreadModel> getAllThreads() {
        return threadService.findAllThreads();
    }

    @GET
    @Path("/post/{postId}")
    public List<ThreadModel> getThreadsByPostId(@PathParam("postId") String postId) {
        return threadService.findThreadsByPostId(postId);
    }

    @PUT
    @Path("/{id}")
    public ThreadModel updateThread(@PathParam("id") String id,
                                    UpdateThreadModel updateThreadModel) {
        // Ensure ID matches or handle separately. Here assuming Model contains correct
        // data.
        if (!id.equals(updateThreadModel.id())) {
            throw new IllegalArgumentException("ID mismatch");
        }
        return threadService.updateThread(updateThreadModel);
    }

    @DELETE
    @Path("/{id}")
    public void deleteThread(@PathParam("id") String id) {
        threadService.deleteThreadById(id);
    }
}
