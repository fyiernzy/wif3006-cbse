package assignment.wif3006cbse.features.project.domain.repository;

import assignment.wif3006cbse.features.project.domain.entity.Applicant;
import assignment.wif3006cbse.features.project.domain.entity.ApplicantStatus;
import assignment.wif3006cbse.shared.spi.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Applicant entity (ApplicantDAO).
 * Dependency Depth 1 - depends on Project and User.
 */
public interface ApplicantRepository extends CrudRepository<Applicant, String> {

    /**
     * Find all applicants for a specific project.
     */
    List<Applicant> findByProjectId(String projectId);

    /**
     * Find all applications by a specific user.
     */
    List<Applicant> findByUserId(String userId);

    /**
     * Find applicants by status.
     */
    List<Applicant> findByStatus(ApplicantStatus status);

    /**
     * Find application by project and user combination.
     */
    Optional<Applicant> findByProjectIdAndUserId(String projectId, String userId);

    /**
     * Find accepted applicant for a project.
     */
    Optional<Applicant> findAcceptedApplicantForProject(String projectId);

    /**
     * Check if user has already applied to a project.
     */
    boolean hasUserApplied(String projectId, String userId);
}
