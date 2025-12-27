package assignment.wif3006cbse.features.project.domain.repository;

import assignment.wif3006cbse.features.project.domain.entity.Review;
import assignment.wif3006cbse.shared.spi.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Review entity (ReviewDAO).
 * Dependency Depth 1 - depends on Project.
 */
public interface ReviewRepository extends CrudRepository<Review, String> {

    /**
     * Find review for a specific project.
     */
    Optional<Review> findByProjectId(String projectId);

    /**
     * Find all reviews given by a specific reviewer.
     */
    List<Review> findByReviewerId(String reviewerId);

    /**
     * Find all reviews received by a specific reviewee.
     */
    List<Review> findByRevieweeId(String revieweeId);

    /**
     * Check if a project has been reviewed.
     */
    boolean isProjectReviewed(String projectId);

    /**
     * Calculate average rating for a user (reviewee).
     */
    double calculateAverageRatingForUser(String userId);
}
