package assignment.wif3006cbse.features.project.web;

import assignment.wif3006cbse.features.project.application.dto.review.CreateReviewModel;
import assignment.wif3006cbse.features.project.application.dto.review.ReviewModel;
import assignment.wif3006cbse.features.project.application.service.ReviewService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * REST Controller for Review operations.
 * Dependency Depth 4 - depends on ReviewService.
 * Provides endpoints for project reviews and ratings.
 */
@Component(
    service = ReviewController.class,
    property = {
        "osgi.jaxrs.resource=true",
        "osgi.jaxrs.application.select=(osgi.jaxrs.name=main)",
    }
)
@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewController {

    @Reference
    private ReviewService reviewService;

    /**
     * Create a review for a project.
     */
    @POST
    public Response createReview(CreateReviewModel model) {
        try {
            ReviewModel created = reviewService.createReview(model);
            return Response.status(Response.Status.CREATED).entity(created).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        } catch (IllegalStateException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }

    /**
     * Get review by ID.
     */
    @GET
    @Path("/{reviewId}")
    public Response getReviewById(@PathParam("reviewId") String reviewId) {
        return reviewService.getReviewById(reviewId)
                .map(review -> Response.ok(review).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Review not found"))
                        .build());
    }

    /**
     * Get review for a specific project.
     */
    @GET
    @Path("/project/{projectId}")
    public Response getReviewByProjectId(@PathParam("projectId") String projectId) {
        return reviewService.getReviewByProjectId(projectId)
                .map(review -> Response.ok(review).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Review not found for this project"))
                        .build());
    }

    /**
     * Get all reviews given by a reviewer.
     */
    @GET
    @Path("/reviewer/{reviewerId}")
    public Response getReviewsByReviewerId(@PathParam("reviewerId") String reviewerId) {
        List<ReviewModel> reviews = reviewService.getReviewsByReviewerId(reviewerId);
        return Response.ok(reviews).build();
    }

    /**
     * Get all reviews received by a reviewee (freelancer).
     */
    @GET
    @Path("/reviewee/{revieweeId}")
    public Response getReviewsByRevieweeId(@PathParam("revieweeId") String revieweeId) {
        List<ReviewModel> reviews = reviewService.getReviewsByRevieweeId(revieweeId);
        return Response.ok(reviews).build();
    }

    /**
     * Check if a project has been reviewed.
     */
    @GET
    @Path("/project/{projectId}/exists")
    public Response isProjectReviewed(@PathParam("projectId") String projectId) {
        boolean reviewed = reviewService.isProjectReviewed(projectId);
        return Response.ok(new ReviewExistsResponse(reviewed)).build();
    }

    /**
     * Get average rating for a user.
     */
    @GET
    @Path("/user/{userId}/rating")
    public Response getAverageRatingForUser(@PathParam("userId") String userId) {
        double rating = reviewService.getAverageRatingForUser(userId);
        return Response.ok(new AverageRatingResponse(rating)).build();
    }

    /**
     * Delete a review.
     */
    @DELETE
    @Path("/{reviewId}")
    public Response deleteReview(@PathParam("reviewId") String reviewId) {
        boolean deleted = reviewService.deleteReview(reviewId);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse("Review not found"))
                .build();
    }

    /**
     * Response wrappers.
     */
    public record ErrorResponse(String message) {}
    public record ReviewExistsResponse(boolean exists) {}
    public record AverageRatingResponse(double averageRating) {}
}
