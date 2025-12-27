package assignment.wif3006cbse.features.project.application.service;

import assignment.wif3006cbse.features.project.application.dto.deliverable.CreateUploadedFileModel;
import assignment.wif3006cbse.features.project.application.dto.deliverable.UploadedFileModel;
import assignment.wif3006cbse.features.project.domain.entity.NotificationType;
import assignment.wif3006cbse.features.project.domain.entity.Project;
import assignment.wif3006cbse.features.project.domain.entity.UploadedFile;
import assignment.wif3006cbse.features.project.domain.repository.ProjectRepository;
import assignment.wif3006cbse.features.project.domain.repository.UploadedFilesRepository;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of DeliverableService.
 * Dependency Depth 3 - depends on ProjectRepository (ProjectDAO), UploadedFilesRepository (UploadedFilesDAO), NotificationService.
 */
@Component(service = DeliverableService.class)
public class DeliverableServiceImpl implements DeliverableService {

    @Reference
    private UploadedFilesRepository uploadedFilesRepository;

    @Reference
    private ProjectRepository projectRepository;

    @Reference
    private NotificationService notificationService;

    @Override
    public UploadedFileModel uploadFile(CreateUploadedFileModel model) {
        // Validate project exists and user is the service provider
        Optional<Project> projectOpt = projectRepository.findById(model.projectId());
        if (projectOpt.isEmpty()) {
            throw new IllegalArgumentException("Project not found: " + model.projectId());
        }

        Project project = projectOpt.get();
        if (!model.uploadedBy().equals(project.getServiceProvider())) {
            throw new IllegalStateException("Only the assigned service provider can upload deliverables");
        }

        UploadedFile file = new UploadedFile(
            model.projectId(),
            model.uploadedBy(),
            model.fileName(),
            model.fileUrl(),
            model.originalFileName(),
            model.contentType(),
            model.fileSize()
        );

        UploadedFile saved = uploadedFilesRepository.save(file);
        return toUploadedFileModel(saved);
    }

    @Override
    public Optional<UploadedFileModel> getFileById(String fileId) {
        return uploadedFilesRepository.findById(fileId)
                .map(this::toUploadedFileModel);
    }

    @Override
    public List<UploadedFileModel> getFilesByProjectId(String projectId) {
        return uploadedFilesRepository.findByProjectId(projectId).stream()
                .map(this::toUploadedFileModel)
                .toList();
    }

    @Override
    public List<UploadedFileModel> getFilesByUploadedBy(String userId) {
        return uploadedFilesRepository.findByUploadedBy(userId).stream()
                .map(this::toUploadedFileModel)
                .toList();
    }

    @Override
    public boolean deleteFile(String fileId) {
        return uploadedFilesRepository.deleteById(fileId);
    }

    @Override
    public void deleteFilesByProjectId(String projectId) {
        uploadedFilesRepository.deleteByProjectId(projectId);
    }

    @Override
    public void submitDeliverablesForReview(String projectId, String submitterId) {
        Optional<Project> projectOpt = projectRepository.findById(projectId);
        if (projectOpt.isEmpty()) {
            throw new IllegalArgumentException("Project not found: " + projectId);
        }

        Project project = projectOpt.get();
        
        // Notify project owner that deliverables have been submitted
        notificationService.sendNotification(
            project.getPostedBy(),
            submitterId,
            "Deliverables have been submitted for review for project: " + project.getProjectTitle(),
            NotificationType.FILE_UPLOADED
        );
    }

    @Override
    public void acceptDeliverables(String projectId, String reviewerId) {
        Optional<Project> projectOpt = projectRepository.findById(projectId);
        if (projectOpt.isEmpty()) {
            throw new IllegalArgumentException("Project not found: " + projectId);
        }

        Project project = projectOpt.get();
        
        // Update project status
        project.setFileAccepted(true);
        project.updateTimestamp();
        projectRepository.save(project);

        // Notify the service provider
        notificationService.sendNotification(
            project.getServiceProvider(),
            reviewerId,
            "Your deliverables for project '" + project.getProjectTitle() + "' have been accepted!",
            NotificationType.FILE_ACCEPTED
        );
    }

    @Override
    public void rejectDeliverables(String projectId, String reviewerId, String reason) {
        Optional<Project> projectOpt = projectRepository.findById(projectId);
        if (projectOpt.isEmpty()) {
            throw new IllegalArgumentException("Project not found: " + projectId);
        }

        Project project = projectOpt.get();

        // Notify the service provider with rejection reason
        String message = "Your deliverables for project '" + project.getProjectTitle() + 
                        "' have been rejected. Reason: " + reason;
        notificationService.sendNotification(
            project.getServiceProvider(),
            reviewerId,
            message,
            NotificationType.FILE_REJECTED
        );
    }

    private UploadedFileModel toUploadedFileModel(UploadedFile file) {
        return new UploadedFileModel(
            file.getId(),
            file.getProjectId(),
            file.getUploadedBy(),
            file.getFileName(),
            file.getFileUrl(),
            file.getOriginalFileName(),
            file.getContentType(),
            file.getFileSize(),
            file.getSubmittedAt()
        );
    }
}
