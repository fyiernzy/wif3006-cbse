package assignment.wif3006cbse.features.community.web;

import assignment.wif3006cbse.features.community.application.dto.comment.CommentModel;
import assignment.wif3006cbse.features.community.application.dto.comment.CreateCommentModel;
import assignment.wif3006cbse.features.community.application.dto.comment.UpdateCommentModel;
import assignment.wif3006cbse.features.community.application.service.CommentService;
import assignment.wif3006cbse.shared.pagination.Page;
import assignment.wif3006cbse.shared.pagination.Pageable;
import assignment.wif3006cbse.shared.pagination.PagedModel;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response createComment(CreateCommentModel createCommentModel) {
        CommentModel created = commentService.createComment(createCommentModel);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @Path("/{threadId}")
    public Response findCommentsByThreadId(@PathParam("threadId") String threadId,
                                           @QueryParam("page") @DefaultValue("0") int page,
                                           @QueryParam("size") @DefaultValue("20") int size) {
        Page<CommentModel> comments = commentService.findCommentsByThreadId(threadId,
            Pageable.of(page, size));
        PagedModel<CommentModel> pageModel = new PagedModel<>(comments);
        return Response.ok(pageModel).build();
    }

    @PUT
    public Response updateComment(UpdateCommentModel updateCommentModel) {
        CommentModel updated = commentService.updateComment(updateCommentModel);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteComment(@PathParam("id") String id) {
        commentService.deleteCommentById(id);
        return Response.noContent().build();
    }
}
