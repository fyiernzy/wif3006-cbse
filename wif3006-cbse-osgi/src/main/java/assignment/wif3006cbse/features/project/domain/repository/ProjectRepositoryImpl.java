package assignment.wif3006cbse.features.project.domain.repository;

import assignment.wif3006cbse.features.project.domain.entity.Project;
import assignment.wif3006cbse.shared.FileBasedRepository;
import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * File-based implementation of ProjectRepository.
 * Corresponds to ProjectDAO (Depth 1).
 */
@Component(service = ProjectRepository.class)
public class ProjectRepositoryImpl extends FileBasedRepository<Project, String> implements ProjectRepository {

    public ProjectRepositoryImpl() {
        super("projects.json", Project.class, Project::getId);
    }

    @Override
    public List<Project> findByPostedBy(String userId) {
        return findAll().stream()
                .filter(p -> userId.equals(p.getPostedBy()))
                .toList();
    }

    @Override
    public List<Project> findByServiceProvider(String userId) {
        return findAll().stream()
                .filter(p -> userId.equals(p.getServiceProvider()))
                .toList();
    }

    @Override
    public List<Project> findAvailableProjects() {
        return findAll().stream()
                .filter(p -> p.isPosted() && !p.isTaken())
                .toList();
    }

    @Override
    public List<Project> findByCategory(String category) {
        return findAll().stream()
                .filter(p -> category.equalsIgnoreCase(p.getProjectCategory()))
                .toList();
    }

    @Override
    public List<Project> findByFilters(List<String> filters) {
        if (filters == null || filters.isEmpty()) {
            return findAll();
        }
        return findAll().stream()
                .filter(p -> p.getFilters() != null && 
                        p.getFilters().stream().anyMatch(filters::contains))
                .toList();
    }

    @Override
    public List<Project> findCompletedProjects() {
        return findAll().stream()
                .filter(Project::isCompleted)
                .toList();
    }

    @Override
    public List<Project> findPendingReviewProjects() {
        return findAll().stream()
                .filter(p -> p.isTaken() && !p.isCompleted() && p.isFileAccepted())
                .toList();
    }
}
