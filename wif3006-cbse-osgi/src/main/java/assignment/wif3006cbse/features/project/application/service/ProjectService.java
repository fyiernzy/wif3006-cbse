package assignment.wif3006cbse.features.project.application.service;

import assignment.wif3006cbse.features.project.application.dto.project.CreateProjectModel;
import assignment.wif3006cbse.features.project.application.dto.project.ProjectListModel;
import assignment.wif3006cbse.features.project.application.dto.project.ProjectModel;
import assignment.wif3006cbse.features.project.application.dto.project.UpdateProjectModel;
import assignment.wif3006cbse.features.profile.application.dto.user.UserModel;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for Project operations.
 * Dependency Depth 2 - depends on ProjectDAO.
 */
public interface ProjectService {

    /**
     * Create a new project.
     */
    ProjectModel createProject(CreateProjectModel model);

    /**
     * Get project by ID.
     */
    Optional<ProjectModel> getProjectById(String projectId);

    /**
     * Get all projects.
     */
    List<ProjectListModel> getAllProjects();

    /**
     * Get available projects (not yet taken).
     */
    List<ProjectListModel> getAvailableProjects();

    /**
     * Get projects posted by a specific user.
     */
    List<ProjectListModel> getProjectsByPostedBy(String userId);

    /**
     * Get projects assigned to a freelancer.
     */
    List<ProjectListModel> getProjectsByServiceProvider(String userId);

    /**
     * Get projects by category.
     */
    List<ProjectListModel> getProjectsByCategory(String category);

    /**
     * Get projects by filters.
     */
    List<ProjectListModel> getProjectsByFilters(List<String> filters);

    /**
     * Update an existing project.
     */
    Optional<ProjectModel> updateProject(String projectId, UpdateProjectModel model);

    /**
     * Delete a project.
     */
    boolean deleteProject(String projectId);

    /**
     * Mark a project as taken by a freelancer.
     */
    Optional<ProjectModel> assignProject(String projectId, String serviceProviderId);

    /**
     * Mark a project as completed.
     */
    Optional<ProjectModel> completeProject(String projectId);

    /**
     * Accept the deliverables for a project.
     */
    Optional<ProjectModel> acceptDeliverables(String projectId);

    UserModel saveFavoriteProject(String userId, String projectId);

    List<ProjectModel> getFavoriteProjects(String userId);

    UserModel removeFavoriteProject(String userId, String projectId);

    void addApplyingProject(String userId, String projectId);

    void removeApplyingProject(String userId, String projectId);

    List<ProjectModel> getApplyingProjects(String userId);

    UserModel saveTakenProject(String userId, String projectId);

    List<ProjectModel> getTakenProjects(String userId);

    UserModel saveCompletedProject(String userId, String projectId);

    List<ProjectModel> getCompletedProjects(String userId);
}
