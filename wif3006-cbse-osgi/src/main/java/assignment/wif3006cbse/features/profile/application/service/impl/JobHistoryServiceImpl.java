package assignment.wif3006cbse.features.profile.application.service.impl;

import assignment.wif3006cbse.features.profile.application.dto.jobhistory.JobHistoryModel;
import assignment.wif3006cbse.features.profile.application.service.JobHistoryService;
import assignment.wif3006cbse.features.profile.domain.entity.JobHistory;
import assignment.wif3006cbse.features.profile.domain.repository.JobHistoryRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component(service = JobHistoryService.class)
public class JobHistoryServiceImpl implements JobHistoryService {

    private final JobHistoryRepository jobHistoryRepository;

    @Activate
    public JobHistoryServiceImpl(@Reference JobHistoryRepository jobHistoryRepository) {
        this.jobHistoryRepository = jobHistoryRepository;
    }

    public JobHistoryServiceImpl() {
        this.jobHistoryRepository = null;
    }

    @Override
    public JobHistoryModel findJobHistoryById(String id) {
        JobHistory jobHistory = jobHistoryRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Job history not found: " + id));
        return toModel(jobHistory);
    }

    @Override
    public List<JobHistoryModel> findAllByUserId(String userId) {
        return jobHistoryRepository.findAllByUserId(userId).stream()
            .sorted(Comparator.comparing(JobHistory::getStartedAt).reversed())
            .map(this::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public List<JobHistoryModel> findAllByStatus(String status) {
        return jobHistoryRepository.findAllByStatus(status).stream()
            .sorted(Comparator.comparing(JobHistory::getStartedAt).reversed())
            .map(this::toModel)
            .collect(Collectors.toList());
    }

    private JobHistoryModel toModel(JobHistory jobHistory) {
        return new JobHistoryModel(
            jobHistory.getId(),
            jobHistory.getUserId(),
            jobHistory.getProjectId(),
            jobHistory.getProjectTitle(),
            jobHistory.getRole(),
            jobHistory.getStatus(),
            jobHistory.getRating(),
            jobHistory.getReview(),
            jobHistory.getDeliverables(),
            jobHistory.getStartedAt(),
            jobHistory.getCompletedAt(),
            jobHistory.getCreatedAt()
        );
    }
}
