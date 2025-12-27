package assignment.wif3006cbse.features.project.application.service;

import assignment.wif3006cbse.features.project.application.dto.project.CreateProjectModel;
import assignment.wif3006cbse.features.project.application.dto.project.ProjectListModel;
import assignment.wif3006cbse.features.project.application.dto.project.ProjectModel;
import assignment.wif3006cbse.features.project.application.dto.project.UpdateProjectModel;
import assignment.wif3006cbse.features.project.domain.entity.Project;
import assignment.wif3006cbse.features.project.domain.repository.ProjectRepository;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of ProjectService.
 * Dependency Depth 2 - depends on ProjectRepository (ProjectDAO).
 */
@Component(service = ProjectService.class)
public class ProjectServiceImpl implements ProjectService {

    @Reference
    private ProjectRepository projectRepository;

    @Override
    public ProjectModel createProject(CreateProjectModel model) {
        Project project = new Project(
            model.postedBy(),
            model.projectTitle(),
            model.projectDescription(),
            model.location(),
            model.projectCategory(),
            model.projectDuration(),
            model.requiredSkills(),
            model.projectBudget(),
            model.deadline(),
            model.contactInformation(),
            model.additionalNotes(),
            model.agreedToTerms()
        );
        project.setCompanyName(model.companyName());
        
        Project saved = projectRepository.save(project);
        return toProjectModel(saved);
    }

    @Override
    public Optional<ProjectModel> getProjectById(String projectId) {
        return projectRepository.findById(projectId)
                .map(this::toProjectModel);
    }

    @Override
    public List<ProjectListModel> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::toProjectListModel)
                .toList();
    }

    @Override
    public List<ProjectListModel> getAvailableProjects() {
        return projectRepository.findAvailableProjects().stream()
                .map(this::toProjectListModel)
                .toList();
    }

    @Override
    public List<ProjectListModel> getProjectsByPostedBy(String userId) {
        return projectRepository.findByPostedBy(userId).stream()
                .map(this::toProjectListModel)
                .toList();
    }

    @Override
    public List<ProjectListModel> getProjectsByServiceProvider(String userId) {
        return projectRepository.findByServiceProvider(userId).stream()
                .map(this::toProjectListModel)
                .toList();
    }

    @Override
    public List<ProjectListModel> getProjectsByCategory(String category) {
        return projectRepository.findByCategory(category).stream()
                .map(this::toProjectListModel)
                .toList();
    }

    @Override
    public List<ProjectListModel> getProjectsByFilters(List<String> filters) {
        return projectRepository.findByFilters(filters).stream()
                .map(this::toProjectListModel)
                .toList();
    }

    @Override
    public Optional<ProjectModel> updateProject(String projectId, UpdateProjectModel model) {
        return projectRepository.findById(projectId)
                .map(project -> {
                    if (model.companyName() != null) project.setCompanyName(model.companyName());
                    if (model.projectTitle() != null) project.setProjectTitle(model.projectTitle());
                    if (model.projectDescription() != null) project.setProjectDescription(model.projectDescription());
                    if (model.location() != null) project.setLocation(model.location());
                    if (model.projectCategory() != null) project.setProjectCategory(model.projectCategory());
                    if (model.projectDuration() != null) project.setProjectDuration(model.projectDuration());
                    if (model.requiredSkills() != null) project.setRequiredSkills(model.requiredSkills());
                    if (model.projectBudget() != null) project.setProjectBudget(model.projectBudget());
                    if (model.deadline() != null) project.setDeadline(model.deadline());
                    if (model.contactInformation() != null) project.setContactInformation(model.contactInformation());
                    if (model.additionalNotes() != null) project.setAdditionalNotes(model.additionalNotes());
                    
                    project.refreshFilters();
                    project.updateTimestamp();
                    
                    return projectRepository.save(project);
                })
                .map(this::toProjectModel);
    }

    @Override
    public boolean deleteProject(String projectId) {
        return projectRepository.deleteById(projectId);
    }

    @Override
    public Optional<ProjectModel> assignProject(String projectId, String serviceProviderId) {
        return projectRepository.findById(projectId)
                .map(project -> {
                    project.setServiceProvider(serviceProviderId);
                    project.setTaken(true);
                    project.updateTimestamp();
                    return projectRepository.save(project);
                })
                .map(this::toProjectModel);
    }

    @Override
    public Optional<ProjectModel> completeProject(String projectId) {
        return projectRepository.findById(projectId)
                .map(project -> {
                    project.setCompleted(true);
                    project.updateTimestamp();
                    return projectRepository.save(project);
                })
                .map(this::toProjectModel);
    }

    @Override
    public Optional<ProjectModel> acceptDeliverables(String projectId) {
        return projectRepository.findById(projectId)
                .map(project -> {
                    project.setFileAccepted(true);
                    project.updateTimestamp();
                    return projectRepository.save(project);
                })
                .map(this::toProjectModel);
    }

    private ProjectModel toProjectModel(Project project) {
        return new ProjectModel(
            project.getId(),
            project.getPostedBy(),
            project.getCompanyName(),
            project.getProjectTitle(),
            project.getProjectDescription(),
            project.getLocation(),
            project.getProjectCategory(),
            project.getProjectDuration(),
            project.getFilters() != null ? new ArrayList<>(project.getFilters()) : new ArrayList<>(),
            project.getRequiredSkills() != null ? new ArrayList<>(project.getRequiredSkills()) : new ArrayList<>(),
            project.getProjectBudget(),
            project.getDeadline(),
            project.getContactInformation(),
            project.getAdditionalNotes(),
            project.isAgreedToTerms(),
            project.isPosted(),
            project.isTaken(),
            project.isCompleted(),
            project.isFileAccepted(),
            project.getServiceProvider(),
            project.getCreatedAt(),
            project.getUpdatedAt()
        );
    }

    private ProjectListModel toProjectListModel(Project project) {
        return new ProjectListModel(
            project.getId(),
            project.getCompanyName(),
            project.getProjectTitle(),
            project.getProjectCategory(),
            project.getProjectDuration(),
            project.getLocation(),
            project.getRequiredSkills() != null ? new ArrayList<>(project.getRequiredSkills()) : new ArrayList<>(),
            project.getProjectBudget(),
            project.getDeadline(),
            project.isTaken(),
            project.isCompleted()
        );
    }
}
