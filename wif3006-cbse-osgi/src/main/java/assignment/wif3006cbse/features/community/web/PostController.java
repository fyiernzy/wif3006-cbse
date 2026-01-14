package assignment.wif3006cbse.features.community.web;

import assignment.wif3006cbse.features.community.application.dto.post.CreatePostModel;
import assignment.wif3006cbse.features.community.application.dto.post.PostModel;
import assignment.wif3006cbse.features.community.application.dto.post.UpdatePostModel;
import assignment.wif3006cbse.features.community.application.service.PostService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component(service = { PostController.class }, property = {
        "osgi.jaxrs.resource=true",
        "osgi.jaxrs.application.select=(osgi.jaxrs.name=main)",
})
@Path("/api/v1/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostController {

    @Reference
    private PostService postService;

    @POST
    public Response createPost(CreatePostModel createPostModel) {
        PostModel created = postService.createPost(createPostModel);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @Path("/{id}")
    public Response findPostById(@PathParam("id") String id) {
        PostModel post = postService.findPostById(id);
        return Response.ok(post).build();
    }

    @GET
    @Path("/author/{id}")
    public Response findPostsByAuthorId(
            @PathParam("id") String authorId,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size) {
        List<PostModel> all = postService.findAllByAuthorIdOrderByCreatedAtDesc(authorId);
        int fromIndex = page * size;
        if (fromIndex >= all.size()) {
            return Response.ok(java.util.Collections.emptyList()).build();
        }
        int toIndex = Math.min(fromIndex + size, all.size());
        return Response.ok(all.subList(fromIndex, toIndex)).build();
    }

    @PUT
    public Response updatePost(UpdatePostModel updatePostModel) {
        PostModel updated = postService.updatePost(updatePostModel);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePost(@PathParam("id") String id) {
        postService.deletePostById(id);
        return Response.noContent().build();
    }
}
