package assignment.wif3006cbse.features.project.application.service;

import assignment.wif3006cbse.features.project.application.dto.applicant.ApplicantModel;
import assignment.wif3006cbse.features.project.application.dto.applicant.CreateApplicantModel;
import assignment.wif3006cbse.features.project.domain.entity.Applicant;
import assignment.wif3006cbse.features.project.domain.entity.ApplicantStatus;
import assignment.wif3006cbse.features.project.domain.entity.NotificationType;
import assignment.wif3006cbse.features.project.domain.entity.Project;
import assignment.wif3006cbse.features.project.domain.repository.ApplicantRepository;
import assignment.wif3006cbse.features.project.domain.repository.ProjectRepository;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of ApplicationService.
 * Dependency Depth 2 - depends on ProjectRepository (ProjectDAO), ApplicantRepository (ApplicantDAO).
 * Also uses NotificationService to send notifications.
 */
@Component(service = ApplicationService.class)
public class ApplicationServiceImpl implements ApplicationService {

    @Reference
    private ApplicantRepository applicantRepository;

    @Reference
    private ProjectRepository projectRepository;

    @Reference
    private NotificationService notificationService;

    @Override
    public ApplicantModel applyToProject(CreateApplicantModel model) {
        // Check if project exists and is available
        Optional<Project> projectOpt = projectRepository.findById(model.projectId());
        if (projectOpt.isEmpty()) {
            throw new IllegalArgumentException("Project not found: " + model.projectId());
        }
        
        Project project = projectOpt.get();
        if (project.isTaken()) {
            throw new IllegalStateException("Project is already taken");
        }
        
        // Check if user has already applied
        if (applicantRepository.hasUserApplied(model.projectId(), model.userId())) {
            throw new IllegalStateException("User has already applied to this project");
        }

        Applicant applicant = new Applicant(
            model.projectId(),
            model.userId(),
            model.coverLetter()
        );

        Applicant saved = applicantRepository.save(applicant);

        // Send notification to project owner
        notificationService.sendNotification(
            project.getPostedBy(),
            model.userId(),
            "New application received for project: " + project.getProjectTitle(),
            NotificationType.APPLICATION_RECEIVED
        );

        return toApplicantModel(saved);
    }

    @Override
    public Optional<ApplicantModel> getApplicationById(String applicationId) {
        return applicantRepository.findById(applicationId)
                .map(this::toApplicantModel);
    }

    @Override
    public List<ApplicantModel> getApplicationsByProjectId(String projectId) {
        return applicantRepository.findByProjectId(projectId).stream()
                .map(this::toApplicantModel)
                .toList();
    }

    @Override
    public List<ApplicantModel> getApplicationsByUserId(String userId) {
        return applicantRepository.findByUserId(userId).stream()
                .map(this::toApplicantModel)
                .toList();
    }

    @Override
    public Optional<ApplicantModel> acceptApplication(String applicationId) {
        return applicantRepository.findById(applicationId)
                .map(applicant -> {
                    // Update applicant status
                    applicant.setStatus(ApplicantStatus.ACCEPTED);
                    applicantRepository.save(applicant);

                    // Get project and update it
                    projectRepository.findById(applicant.getProjectId())
                            .ifPresent(project -> {
                                project.setServiceProvider(applicant.getUserId());
                                project.setTaken(true);
                                project.updateTimestamp();
                                projectRepository.save(project);

                                // Send notification to the accepted applicant
                                notificationService.sendNotification(
                                    applicant.getUserId(),
                                    project.getPostedBy(),
                                    "Your application for project '" + project.getProjectTitle() + "' has been accepted!",
                                    NotificationType.APPLICATION_ACCEPTED
                                );
                            });

                    // Reject all other pending applications for this project
                    applicantRepository.findByProjectId(applicant.getProjectId()).stream()
                            .filter(a -> !a.getId().equals(applicationId) && 
                                        ApplicantStatus.PENDING.equals(a.getStatus()))
                            .forEach(a -> {
                                a.setStatus(ApplicantStatus.REJECTED);
                                applicantRepository.save(a);
                                
                                // Notify rejected applicants
                                projectRepository.findById(applicant.getProjectId())
                                    .ifPresent(project -> notificationService.sendNotification(
                                        a.getUserId(),
                                        project.getPostedBy(),
                                        "Your application for project '" + project.getProjectTitle() + "' was not selected.",
                                        NotificationType.APPLICATION_REJECTED
                                    ));
                            });

                    return applicant;
                })
                .map(this::toApplicantModel);
    }

    @Override
    public Optional<ApplicantModel> rejectApplication(String applicationId) {
        return applicantRepository.findById(applicationId)
                .map(applicant -> {
                    applicant.setStatus(ApplicantStatus.REJECTED);
                    applicantRepository.save(applicant);

                    // Send notification to the rejected applicant
                    projectRepository.findById(applicant.getProjectId())
                            .ifPresent(project -> notificationService.sendNotification(
                                applicant.getUserId(),
                                project.getPostedBy(),
                                "Your application for project '" + project.getProjectTitle() + "' was rejected.",
                                NotificationType.APPLICATION_REJECTED
                            ));

                    return applicant;
                })
                .map(this::toApplicantModel);
    }

    @Override
    public Optional<ApplicantModel> withdrawApplication(String applicationId) {
        return applicantRepository.findById(applicationId)
                .map(applicant -> {
                    applicant.setStatus(ApplicantStatus.WITHDRAWN);
                    return applicantRepository.save(applicant);
                })
                .map(this::toApplicantModel);
    }

    @Override
    public boolean hasUserApplied(String projectId, String userId) {
        return applicantRepository.hasUserApplied(projectId, userId);
    }

    @Override
    public boolean deleteApplication(String applicationId) {
        return applicantRepository.deleteById(applicationId);
    }

    private ApplicantModel toApplicantModel(Applicant applicant) {
        return new ApplicantModel(
            applicant.getId(),
            applicant.getProjectId(),
            applicant.getUserId(),
            applicant.getStatus() != null ? applicant.getStatus().name() : null,
            applicant.getCoverLetter(),
            applicant.getAppliedAt(),
            applicant.getUpdatedAt()
        );
    }
}
