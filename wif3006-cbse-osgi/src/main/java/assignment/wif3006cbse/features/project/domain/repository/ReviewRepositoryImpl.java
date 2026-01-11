package assignment.wif3006cbse.features.project.domain.repository;

import assignment.wif3006cbse.features.project.domain.entity.Review;
import assignment.wif3006cbse.shared.FileBasedRepository;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.Optional;

/**
 * File-based implementation of ReviewRepository.
 * Corresponds to ReviewDAO (Depth 1).
 */
@Component(service = ReviewRepository.class)
public class ReviewRepositoryImpl extends FileBasedRepository<Review, String> implements ReviewRepository {

    public ReviewRepositoryImpl() {
        super("reviews.json", Review::getId);
    }

    @Override
    public Optional<Review> findByProjectId(String projectId) {
        return findAll().stream()
                .filter(r -> projectId.equals(r.getProjectId()))
                .findFirst();
    }

    @Override
    public List<Review> findByReviewerId(String reviewerId) {
        return findAll().stream()
                .filter(r -> reviewerId.equals(r.getReviewerId()))
                .toList();
    }

    @Override
    public List<Review> findByRevieweeId(String revieweeId) {
        return findAll().stream()
                .filter(r -> revieweeId.equals(r.getRevieweeId()))
                .toList();
    }

    @Override
    public boolean isProjectReviewed(String projectId) {
        return findAll().stream()
                .anyMatch(r -> projectId.equals(r.getProjectId()));
    }

    @Override
    public double calculateAverageRatingForUser(String userId) {
        List<Review> reviews = findByRevieweeId(userId);
        if (reviews.isEmpty()) {
            return 0.0;
        }
        return reviews.stream()
                .mapToDouble(Review::getAverageRating)
                .average()
                .orElse(0.0);
    }
}
