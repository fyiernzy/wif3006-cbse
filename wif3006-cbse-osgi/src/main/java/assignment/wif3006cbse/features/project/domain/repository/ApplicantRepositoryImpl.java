package assignment.wif3006cbse.features.project.domain.repository;

import assignment.wif3006cbse.features.project.domain.entity.Applicant;
import assignment.wif3006cbse.features.project.domain.entity.ApplicantStatus;
import assignment.wif3006cbse.shared.FileBasedRepository;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.Optional;

/**
 * File-based implementation of ApplicantRepository.
 * Corresponds to ApplicantDAO (Depth 1).
 */
@Component(service = ApplicantRepository.class)
public class ApplicantRepositoryImpl extends FileBasedRepository<Applicant, String> implements ApplicantRepository {

    public ApplicantRepositoryImpl() {
        super("applicants.json", Applicant.class, Applicant::getId);
    }

    @Override
    public List<Applicant> findByProjectId(String projectId) {
        return findAll().stream()
                .filter(a -> projectId.equals(a.getProjectId()))
                .toList();
    }

    @Override
    public List<Applicant> findByUserId(String userId) {
        return findAll().stream()
                .filter(a -> userId.equals(a.getUserId()))
                .toList();
    }

    @Override
    public List<Applicant> findByStatus(ApplicantStatus status) {
        return findAll().stream()
                .filter(a -> status.equals(a.getStatus()))
                .toList();
    }

    @Override
    public Optional<Applicant> findByProjectIdAndUserId(String projectId, String userId) {
        return findAll().stream()
                .filter(a -> projectId.equals(a.getProjectId()) && userId.equals(a.getUserId()))
                .findFirst();
    }

    @Override
    public Optional<Applicant> findAcceptedApplicantForProject(String projectId) {
        return findAll().stream()
                .filter(a -> projectId.equals(a.getProjectId()) && 
                        ApplicantStatus.ACCEPTED.equals(a.getStatus()))
                .findFirst();
    }

    @Override
    public boolean hasUserApplied(String projectId, String userId) {
        return findAll().stream()
                .anyMatch(a -> projectId.equals(a.getProjectId()) && userId.equals(a.getUserId()));
    }
}
