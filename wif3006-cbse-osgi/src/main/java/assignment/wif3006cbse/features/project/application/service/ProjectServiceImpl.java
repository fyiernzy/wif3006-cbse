package assignment.wif3006cbse.features.project.application.service;

import assignment.wif3006cbse.features.project.application.dto.project.CreateProjectModel;
import assignment.wif3006cbse.features.project.application.dto.project.ProjectListModel;
import assignment.wif3006cbse.features.project.application.dto.project.ProjectModel;
import assignment.wif3006cbse.features.project.application.dto.project.UpdateProjectModel;
import assignment.wif3006cbse.features.project.domain.entity.Project;
import assignment.wif3006cbse.features.project.domain.repository.ProjectRepository;
import assignment.wif3006cbse.features.profile.application.service.UserService;
import assignment.wif3006cbse.features.profile.application.dto.user.UserModel;
import assignment.wif3006cbse.shared.file.FileStorageService;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Implementation of ProjectService.
 * Dependency Depth 2 - depends on ProjectRepository (ProjectDAO).
 */
@Component(service = ProjectService.class)
public class ProjectServiceImpl implements ProjectService {

    @Reference
    private ProjectRepository projectRepository;

    @Reference
    private UserService userService;

    @Reference
    private FileStorageService fileStorageService;

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
                model.agreedToTerms());
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
                    if (model.companyName() != null)
                        project.setCompanyName(model.companyName());
                    if (model.projectTitle() != null)
                        project.setProjectTitle(model.projectTitle());
                    if (model.projectDescription() != null)
                        project.setProjectDescription(model.projectDescription());
                    if (model.location() != null)
                        project.setLocation(model.location());
                    if (model.projectCategory() != null)
                        project.setProjectCategory(model.projectCategory());
                    if (model.projectDuration() != null)
                        project.setProjectDuration(model.projectDuration());
                    if (model.requiredSkills() != null)
                        project.setRequiredSkills(model.requiredSkills());
                    if (model.projectBudget() != null)
                        project.setProjectBudget(model.projectBudget());
                    if (model.deadline() != null)
                        project.setDeadline(model.deadline());
                    if (model.contactInformation() != null)
                        project.setContactInformation(model.contactInformation());
                    if (model.additionalNotes() != null)
                        project.setAdditionalNotes(model.additionalNotes());

                    project.refreshFilters();
                    project.updateTimestamp();

                    return projectRepository.save(project);
                })
                .map(this::toProjectModel);
    }

    @Override
    public boolean deleteProject(String projectId) {
        projectRepository.deleteById(projectId);
        return true;
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

    @Override
    public UserModel saveFavoriteProject(String userId, String projectId) {
        UserModel userModel = userService.addFavoriteProject(userId, projectId);
        return enrichUserModel(userModel);
    }

    @Override
    public List<ProjectModel> getFavoriteProjects(String userId) {
        UserModel user = userService.findUserById(userId);
        List<String> favIds = user.favoriteProjects();
        return projectRepository.findAllById(favIds).stream()
                .map(this::toProjectModel)
                .toList();
    }

    @Override
    public UserModel removeFavoriteProject(String userId, String projectId) {
        UserModel userModel = userService.removeFavoriteProject(userId, projectId);
        return enrichUserModel(userModel);
    }

    @Override
    public UserModel addApplyingProject(String userId, String projectId) {
        UserModel userModel = userService.addApplyingProject(userId, projectId);
        return enrichUserModel(userModel);
    }

    @Override
    public UserModel removeApplyingProject(String userId, String projectId) {
        UserModel userModel = userService.removeApplyingProject(userId, projectId);
        return enrichUserModel(userModel);
    }

    @Override
    public List<ProjectModel> getApplyingProjects(String userId) {
        UserModel user = userService.findUserById(userId);
        List<String> applyingIds = user.applyingProjects();
        return projectRepository.findAllById(applyingIds).stream()
                .map(this::toProjectModel)
                .toList();
    }

    @Override
    public UserModel saveTakenProject(String userId, String projectId) {
        assignProject(projectId, userId);
        return enrichUserModel(userService.findUserById(userId));
    }

    @Override
    public List<ProjectModel> getTakenProjects(String userId) {
        return projectRepository.findByServiceProvider(userId).stream()
                .map(this::toProjectModel)
                .toList();
    }

    @Override
    public UserModel saveCompletedProject(String userId, String projectId) {
        completeProject(projectId);
        return enrichUserModel(userService.findUserById(userId));
    }

    @Override
    public List<ProjectModel> getCompletedProjects(String userId) {
        return projectRepository.findByServiceProvider(userId).stream()
                .filter(Project::isCompleted)
                .map(this::toProjectModel)
                .toList();
    }

    @Override
    public ProjectModel uploadFiles(String projectId, List<Attachment> attachments) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        List<String> currentIds = project.getUploadedFileIds();
        if (currentIds == null) {
            currentIds = new ArrayList<>();
        }

        List<String> newIds = new ArrayList<>();

        for (Attachment attachment : attachments) {
            try {
                InputStream is = attachment.getDataHandler().getInputStream();
                String fileName = attachment.getContentDisposition().getParameter("filename");

                if (is != null && fileName != null) {
                    // 1. Save to disk via Storage Helper
                    String storedFilename = fileStorageService.store(is, fileName);

                    // 2. Extract UUID (if you want to strip extension as per your previous logic)
                    String fileId = storedFilename;
                    if (storedFilename.contains(".")) {
                        fileId = storedFilename.substring(0, storedFilename.lastIndexOf('.'));
                    }

                    // 3. Keep track of what we just added
                    newIds.add(fileId);
                }
            } catch (IOException e) {
                throw new RuntimeException("Error processing file upload", e);
            }
        }

        // Update repository
        currentIds.addAll(newIds);
        project.setUploadedFileIds(currentIds);
        Project saved = projectRepository.save(project);

        return toProjectModel(saved);
    }

    @Override
    public void streamProjectFiles(String projectId, OutputStream outputStream) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        List<String> fileIds = project.getUploadedFileIds();
        if (fileIds == null || fileIds.isEmpty()) return;

        try (ZipOutputStream zos = new ZipOutputStream(outputStream)) {
            for (String fileId : fileIds) {
                File file = fileStorageService.loadById(fileId);
                if (file != null && file.exists()) {
                    zos.putNextEntry(new ZipEntry(file.getName()));
                    Files.copy(file.toPath(), zos);
                    zos.closeEntry();
                }
            }
            zos.finish();
        } catch (IOException e) {
            throw new RuntimeException("Failed to zip files for project: " + projectId, e);
        }
    }

    private UserModel enrichUserModel(UserModel user) {
        List<String> takenIds = projectRepository.findByServiceProvider(user.id()).stream()
                .map(Project::getId)
                .toList();

        List<String> completedIds = projectRepository.findByServiceProvider(user.id()).stream()
                .filter(Project::isCompleted)
                .map(Project::getId)
                .toList();

        List<String> postedIds = projectRepository.findByPostedBy(user.id()).stream()
                .map(Project::getId)
                .toList();

        return new UserModel(
                user.id(),
                user.email(),
                user.name(),
                user.about(),
                user.location(),
                user.categories(),
                user.skills(),
                user.favoriteProjects(),
                user.applyingProjects(),
                takenIds,
                completedIds,
                postedIds,
                user.isProfilePublic(),
                user.createdAt(),
                user.updatedAt());
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
                project.getUpdatedAt(),
                project.getUploadedFileIds() != null ? new ArrayList<>(project.getUploadedFileIds())
                        : new ArrayList<>());
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
                project.isCompleted());
    }
}
