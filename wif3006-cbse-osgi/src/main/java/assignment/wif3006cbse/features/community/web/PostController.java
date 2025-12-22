package assignment.wif3006cbse.features.community.web;

import assignment.wif3006cbse.features.community.application.dto.CommentModel;
import assignment.wif3006cbse.features.community.application.service.CommentService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Component(
    service = {
        PostController.class,
        Object.class
    },
    property = {
        "osgi.jaxrs.resource=true",
        "osgi.jaxrs.application.select=(osgi.jaxrs.name=community)"
    }
)
@Path("/api/v1/post")
@Produces(MediaType.APPLICATION_JSON)
public class PostController {

    @Reference
    private CommentService commentService;

    @GET
    public CommentModel greet(@QueryParam("name") String name) {
        return new CommentModel("id", "threadId", "authorId", "content");
    }
}
