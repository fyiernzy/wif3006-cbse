package assignment.wif3006cbse.features.profile.domain.repository;

import assignment.wif3006cbse.features.profile.domain.entity.JobHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, String> {

    List<JobHistory> findAllByUserIdOrderByStartDateDesc(String userId);

    Page<JobHistory> findAllByUserIdOrderByStartDateDesc(String userId, Pageable pageable);

    List<JobHistory> findAllByStatus(String status);
}
