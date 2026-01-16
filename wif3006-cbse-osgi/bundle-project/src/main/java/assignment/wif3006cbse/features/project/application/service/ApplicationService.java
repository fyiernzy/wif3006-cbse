package assignment.wif3006cbse.features.project.application.service;

import assignment.wif3006cbse.features.project.application.dto.applicant.ApplicantModel;
import assignment.wif3006cbse.features.project.application.dto.applicant.CreateApplicantModel;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for Application/Applicant operations.
 * Dependency Depth 2 - depends on ProjectDAO, ApplicantDAO.
 * Handles the application workflow for freelancers applying to projects.
 */
public interface ApplicationService {

    /**
     * Apply to a project as a freelancer.
     */
    ApplicantModel applyToProject(CreateApplicantModel model);

    /**
     * Get application by ID.
     */
    Optional<ApplicantModel> getApplicationById(String applicationId);

    /**
     * Get all applications for a project.
     */
    List<ApplicantModel> getApplicationsByProjectId(String projectId);

    /**
     * Get all applications by a user.
     */
    List<ApplicantModel> getApplicationsByUserId(String userId);

    /**
     * Accept an application (assigns the freelancer to the project).
     */
    Optional<ApplicantModel> acceptApplication(String applicationId);

    /**
     * Reject an application.
     */
    Optional<ApplicantModel> rejectApplication(String applicationId);

    /**
     * Withdraw an application.
     */
    Optional<ApplicantModel> withdrawApplication(String applicationId);

    /**
     * Check if user has already applied to a project.
     */
    boolean hasUserApplied(String projectId, String userId);

    /**
     * Delete an application.
     */
    boolean deleteApplication(String applicationId);
}
