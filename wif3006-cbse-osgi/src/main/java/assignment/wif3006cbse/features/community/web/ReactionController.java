package assignment.wif3006cbse.features.community.web;

import assignment.wif3006cbse.features.community.application.dto.reaction.CreateReactionModel;
import assignment.wif3006cbse.features.community.application.dto.reaction.ReactionModel;
import assignment.wif3006cbse.features.community.application.dto.reaction.UpdateReactionModel;
import assignment.wif3006cbse.features.community.application.service.ReactionService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response createReaction(CreateReactionModel createReactionModel) {
        ReactionModel created = reactionService.createReaction(createReactionModel);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @Path("/post/{postId}")
    public Response findReactionsByPostId(@PathParam("postId") String postId) {
        List<ReactionModel> reactions = reactionService.findReactionsByPostId(postId);
        return Response.ok(reactions).build();
    }

    @GET
    @Path("/thread/{threadId}")
    public Response findReactionsByThreadId(@PathParam("threadId") String threadId) {
        List<ReactionModel> reactions = reactionService.findReactionsByThreadId(threadId);
        return Response.ok(reactions).build();
    }

    @GET
    @Path("/comment/{commentId}")
    public Response findReactionsByCommentId(@PathParam("commentId") String commentId) {
        List<ReactionModel> reactions = reactionService.findReactionsByCommentId(commentId);
        return Response.ok(reactions).build();
    }

    @PUT
    public Response updateReaction(UpdateReactionModel updateReactionModel) {
        ReactionModel updated = reactionService.updateReaction(updateReactionModel);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReaction(@PathParam("id") String id) {
        reactionService.deleteReactionById(id);
        return Response.noContent().build();
    }
}
