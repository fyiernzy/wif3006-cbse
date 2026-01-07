package assignment.wif3006cbse.features.community.web;

import assignment.wif3006cbse.features.community.application.dto.reaction.CreateReactionModel;
import assignment.wif3006cbse.features.community.application.dto.reaction.ReactionModel;
import assignment.wif3006cbse.features.community.application.dto.reaction.UpdateReactionModel;
import assignment.wif3006cbse.features.community.application.service.ReactionService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component(service = {ReactionController.class}, property = {
    "osgi.jaxrs.resource=true",
    "osgi.jaxrs.application.select=(osgi.jaxrs.name=main)",
})
@Path("/api/v1/reactions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReactionController {

    @Reference
    private ReactionService reactionService;

    @POST
    public ReactionModel createReaction(CreateReactionModel createReactionModel) {
        return reactionService.createReaction(createReactionModel);
    }

    @GET
    @Path("/post/{postId}")
    public List<ReactionModel> findReactionsByPostId(@PathParam("postId") String postId) {
        return reactionService.findReactionsByPostId(postId);
    }

    @GET
    @Path("/thread/{threadId}")
    public List<ReactionModel> findReactionsByThreadId(@PathParam("threadId") String threadId) {
        return reactionService.findReactionsByThreadId(threadId);
    }

    @GET
    @Path("/comment/{commentId}")
    public List<ReactionModel> findReactionsByCommentId(@PathParam("commentId") String commentId) {
        return reactionService.findReactionsByCommentId(commentId);
    }

    @PUT
    public ReactionModel updateReaction(UpdateReactionModel updateReactionModel) {
        return reactionService.updateReaction(updateReactionModel);
    }

    @DELETE
    @Path("/{id}")
    public void deleteReaction(@PathParam("id") String id) {
        reactionService.deleteReactionById(id);
    }
}
