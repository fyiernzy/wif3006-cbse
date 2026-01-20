package assignment.wif3006cbse.features.profile.domain.repository;

import assignment.wif3006cbse.features.profile.domain.entity.JobHistory;
import assignment.wif3006cbse.shared.spi.CrudRepository;

import java.util.List;

public interface JobHistoryRepository extends CrudRepository<JobHistory, String> {

    List<JobHistory> findAllByUserId(String userId);

    List<JobHistory> findAllByStatus(String status);
}
