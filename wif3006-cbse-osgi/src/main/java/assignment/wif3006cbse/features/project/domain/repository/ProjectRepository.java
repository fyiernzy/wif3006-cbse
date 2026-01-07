package assignment.wif3006cbse.features.project.domain.repository;

import assignment.wif3006cbse.features.project.domain.entity.Project;
import assignment.wif3006cbse.shared.spi.CrudRepository;

import java.util.List;

/**
 * Repository interface for Project entity (ProjectDAO).
 * Dependency Depth 1 - depends on Project model.
 */
public interface ProjectRepository extends CrudRepository<Project, String> {

    /**
     * Find projects by poster/recruiter ID.
     */
    List<Project> findByPostedBy(String userId);

    /**
     * Find projects by service provider (freelancer who took the project).
     */
    List<Project> findByServiceProvider(String userId);

    /**
     * Find all available projects (posted but not taken).
     */
    List<Project> findAvailableProjects();

    /**
     * Find projects by category.
     */
    List<Project> findByCategory(String category);

    /**
     * Find projects matching any of the given filters.
     */
    List<Project> findByFilters(List<String> filters);

    /**
     * Find completed projects.
     */
    List<Project> findCompletedProjects();

    /**
     * Find projects where files have been uploaded but not yet accepted.
     */
    List<Project> findPendingReviewProjects();

    /**
     * Find all projects with the given IDs.
     */
    List<Project> findAllById(List<String> ids);
}
