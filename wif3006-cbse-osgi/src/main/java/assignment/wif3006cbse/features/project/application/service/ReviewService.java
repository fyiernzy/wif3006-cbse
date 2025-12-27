package assignment.wif3006cbse.features.project.application.service;

import assignment.wif3006cbse.features.project.application.dto.review.CreateReviewModel;
import assignment.wif3006cbse.features.project.application.dto.review.ReviewModel;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for Review operations.
 * Dependency Depth 2 - depends on ProjectDAO, ReviewDAO.
 * Handles project reviews and ratings after completion.
 */
public interface ReviewService {

    /**
     * Create a review for a completed project.
     */
    ReviewModel createReview(CreateReviewModel model);

    /**
     * Get review by ID.
     */
    Optional<ReviewModel> getReviewById(String reviewId);

    /**
     * Get review for a specific project.
     */
    Optional<ReviewModel> getReviewByProjectId(String projectId);

    /**
     * Get all reviews given by a reviewer.
     */
    List<ReviewModel> getReviewsByReviewerId(String reviewerId);

    /**
     * Get all reviews received by a reviewee (freelancer).
     */
    List<ReviewModel> getReviewsByRevieweeId(String revieweeId);

    /**
     * Check if a project has been reviewed.
     */
    boolean isProjectReviewed(String projectId);

    /**
     * Calculate average rating for a user.
     */
    double getAverageRatingForUser(String userId);

    /**
     * Delete a review.
     */
    boolean deleteReview(String reviewId);
}
