package assignment.wif3006cbse.features.profile.domain.repository.impl;

import assignment.wif3006cbse.features.profile.domain.entity.JobHistory;
import assignment.wif3006cbse.features.profile.domain.repository.JobHistoryRepository;
import assignment.wif3006cbse.shared.spi.FileBasedRepository;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = JobHistoryRepository.class)
public class JobHistoryRepositoryImpl extends FileBasedRepository<JobHistory, String> implements
    JobHistoryRepository {

    public JobHistoryRepositoryImpl() {
        super("job_histories.dat", JobHistory::getId);
    }

    @Override
    public List<JobHistory> findAllByUserId(String userId) {
        return getStore().values().stream()
            .filter(jh -> jh.getUserId() != null && jh.getUserId().equals(userId))
            .collect(Collectors.toList());
    }

    @Override
    public List<JobHistory> findAllByStatus(String status) {
        return getStore().values().stream()
            .filter(jh -> jh.getStatus() != null && jh.getStatus().equals(status))
            .collect(Collectors.toList());
    }
}
