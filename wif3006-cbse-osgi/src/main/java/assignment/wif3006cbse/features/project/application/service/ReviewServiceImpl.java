package assignment.wif3006cbse.features.project.application.service;

import assignment.wif3006cbse.features.project.application.dto.review.CreateReviewModel;
import assignment.wif3006cbse.features.project.application.dto.review.ReviewModel;
import assignment.wif3006cbse.features.project.domain.entity.NotificationType;
import assignment.wif3006cbse.features.project.domain.entity.Project;
import assignment.wif3006cbse.features.project.domain.entity.Review;
import assignment.wif3006cbse.features.project.domain.repository.ProjectRepository;
import assignment.wif3006cbse.features.project.domain.repository.ReviewRepository;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of ReviewService.
 * Dependency Depth 2 - depends on ProjectRepository (ProjectDAO), ReviewRepository (ReviewDAO).
 * Also uses NotificationService to notify users about reviews.
 */
@Component(service = ReviewService.class)
public class ReviewServiceImpl implements ReviewService {

    @Reference
    private ReviewRepository reviewRepository;

    @Reference
    private ProjectRepository projectRepository;

    @Reference
    private NotificationService notificationService;

    @Override
    public ReviewModel createReview(CreateReviewModel model) {
        // Validate project exists
        Optional<Project> projectOpt = projectRepository.findById(model.projectId());
        if (projectOpt.isEmpty()) {
            throw new IllegalArgumentException("Project not found: " + model.projectId());
        }

        Project project = projectOpt.get();
        
        // Check if project is completed or files accepted (ready for review)
        if (!project.isFileAccepted() && !project.isCompleted()) {
            throw new IllegalStateException("Project must have accepted deliverables or be completed before review");
        }

        // Check if already reviewed
        if (reviewRepository.isProjectReviewed(model.projectId())) {
            throw new IllegalStateException("Project has already been reviewed");
        }

        Review review = new Review(
            model.projectId(),
            model.reviewerId(),
            model.revieweeId(),
            model.satisfactionRating(),
            model.projectRating(),
            model.collaboratorRating(),
            model.projectFeedback(),
            model.collaboratorFeedback()
        );

        Review saved = reviewRepository.save(review);

        // Mark project as completed after review
        project.setCompleted(true);
        project.updateTimestamp();
        projectRepository.save(project);

        // Notify the reviewee about the review
        notificationService.sendNotification(
            model.revieweeId(),
            model.reviewerId(),
            "You have received a review for project: " + project.getProjectTitle(),
            NotificationType.REVIEW_RECEIVED
        );

        return toReviewModel(saved);
    }

    @Override
    public Optional<ReviewModel> getReviewById(String reviewId) {
        return reviewRepository.findById(reviewId)
                .map(this::toReviewModel);
    }

    @Override
    public Optional<ReviewModel> getReviewByProjectId(String projectId) {
        return reviewRepository.findByProjectId(projectId)
                .map(this::toReviewModel);
    }

    @Override
    public List<ReviewModel> getReviewsByReviewerId(String reviewerId) {
        return reviewRepository.findByReviewerId(reviewerId).stream()
                .map(this::toReviewModel)
                .toList();
    }

    @Override
    public List<ReviewModel> getReviewsByRevieweeId(String revieweeId) {
        return reviewRepository.findByRevieweeId(revieweeId).stream()
                .map(this::toReviewModel)
                .toList();
    }

    @Override
    public boolean isProjectReviewed(String projectId) {
        return reviewRepository.isProjectReviewed(projectId);
    }

    @Override
    public double getAverageRatingForUser(String userId) {
        return reviewRepository.calculateAverageRatingForUser(userId);
    }

    @Override
    public boolean deleteReview(String reviewId) {
        return reviewRepository.deleteById(reviewId);
    }

    private ReviewModel toReviewModel(Review review) {
        return new ReviewModel(
            review.getId(),
            review.getProjectId(),
            review.getReviewerId(),
            review.getRevieweeId(),
            review.getSatisfactionRating(),
            review.getProjectRating(),
            review.getCollaboratorRating(),
            review.getProjectFeedback(),
            review.getCollaboratorFeedback(),
            review.getAverageRating(),
            review.getCreatedAt()
        );
    }
}
