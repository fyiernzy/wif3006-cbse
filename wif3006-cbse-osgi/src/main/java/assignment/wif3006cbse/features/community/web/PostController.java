package assignment.wif3006cbse.features.community.web;

import assignment.wif3006cbse.features.community.application.dto.post.CreatePostModel;
import assignment.wif3006cbse.features.community.application.dto.post.PostModel;
import assignment.wif3006cbse.features.community.application.dto.post.UpdatePostModel;
import assignment.wif3006cbse.features.community.application.service.PostService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component(service = {PostController.class}, property = {
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
    public PostModel createPost(CreatePostModel createPostModel) {
        return postService.createPost(createPostModel);
    }

    @GET
    @Path("/{id}")
    public PostModel findPostById(@PathParam("id") String id) {
        return postService.findPostById(id);
    }

    @GET
    @Path("/author/{id}")
    public List<PostModel> findPostsByAuthorId(
        @PathParam("id") String authorId,
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("size") @DefaultValue("20") int size
    ) {
        List<PostModel> all = postService.findAllByAuthorIdOrderByCreatedAtDesc(authorId);
        int fromIndex = page * size;
        if (fromIndex >= all.size()) {
            return java.util.Collections.emptyList();
        }
        int toIndex = Math.min(fromIndex + size, all.size());
        return all.subList(fromIndex, toIndex);
    }

    @PUT
    public PostModel updatePost(UpdatePostModel updatePostModel) {
        return postService.updatePost(updatePostModel);
    }

    @DELETE
    @Path("/{id}")
    public void deletePost(@PathParam("id") String id) {
        postService.deletePostById(id);
    }
}
