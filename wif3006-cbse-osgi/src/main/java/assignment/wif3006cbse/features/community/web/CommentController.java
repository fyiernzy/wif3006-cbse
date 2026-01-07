package assignment.wif3006cbse.features.community.web;

import assignment.wif3006cbse.features.community.application.dto.comment.CommentModel;
import assignment.wif3006cbse.features.community.application.dto.comment.CreateCommentModel;
import assignment.wif3006cbse.features.community.application.dto.comment.UpdateCommentModel;
import assignment.wif3006cbse.features.community.application.service.CommentService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component(service = {CommentController.class}, property = {
    "osgi.jaxrs.resource=true",
    "osgi.jaxrs.application.select=(osgi.jaxrs.name=main)",
})
@Path("/api/v1/comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentController {

    @Reference
    private CommentService commentService;

    @POST
    public CommentModel createComment(CreateCommentModel createCommentModel) {
        return commentService.createComment(createCommentModel);
    }

    @GET
    @Path("/{threadId}")
    public List<CommentModel> getCommentsByThread(@PathParam("threadId") String threadId) {
        return commentService.findCommentsByThreadId(threadId);
    }

    @PUT
    public CommentModel updateComment(UpdateCommentModel updateCommentModel) {
        return commentService.updateComment(updateCommentModel);
    }

    @DELETE
    @Path("/{id}")
    public void deleteComment(@PathParam("id") String id) {
        commentService.deleteCommentById(id);
    }
}
