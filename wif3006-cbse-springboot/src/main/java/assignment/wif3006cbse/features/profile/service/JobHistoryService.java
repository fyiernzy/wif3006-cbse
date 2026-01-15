package assignment.wif3006cbse.features.profile.service;

import assignment.wif3006cbse.features.profile.domain.entity.JobHistory;
import assignment.wif3006cbse.features.profile.domain.repository.JobHistoryRepository;
import assignment.wif3006cbse.features.profile.dto.jobhistory.CreateJobHistoryModel;
import assignment.wif3006cbse.features.profile.dto.jobhistory.JobHistoryModel;
import assignment.wif3006cbse.features.profile.dto.jobhistory.UpdateJobHistoryModel;
import assignment.wif3006cbse.features.profile.mapper.JobHistoryMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class JobHistoryService {

    private final JobHistoryMapper jobHistoryMapper;
    private final JobHistoryRepository jobHistoryRepository;

    @Transactional
    public JobHistoryModel createJobHistory(@Valid CreateJobHistoryModel createJobHistoryModel) {
        JobHistory jobHistory = jobHistoryMapper.toEntity(createJobHistoryModel);
        if (jobHistory.getStatus() == null || jobHistory.getStatus().isBlank()) {
            jobHistory.setStatus("Ongoing");
        }
        return jobHistoryMapper.toModel(jobHistoryRepository.save(jobHistory));
    }

    @Transactional(readOnly = true)
    public JobHistoryModel findJobHistoryById(@NotBlank String id) {
        return jobHistoryRepository.findById(id)
            .map(jobHistoryMapper::toModel)
            .orElseThrow(() -> new EntityNotFoundException("Job history not found."));
    }

    @Transactional(readOnly = true)
    public List<JobHistoryModel> findAllByUserId(@NotBlank String userId) {
        return jobHistoryRepository.findAllByUserIdOrderByStartDateDesc(userId)
            .stream()
            .map(jobHistoryMapper::toModel)
            .toList();
    }

    @Transactional(readOnly = true)
    public Page<JobHistoryModel> findAllByUserId(@NotBlank String userId, Pageable pageable) {
        return jobHistoryRepository.findAllByUserIdOrderByStartDateDesc(userId, pageable)
            .map(jobHistoryMapper::toModel);
    }

    @Transactional(readOnly = true)
    public List<JobHistoryModel> findAllByStatus(@NotBlank String status) {
        return jobHistoryRepository.findAllByStatus(status)
            .stream()
            .map(jobHistoryMapper::toModel)
            .toList();
    }

    @Transactional
    public JobHistoryModel updateJobHistory(@Valid UpdateJobHistoryModel updateJobHistoryModel) {
        JobHistory jobHistory = jobHistoryRepository.findById(updateJobHistoryModel.id())
            .orElseThrow(() -> new EntityNotFoundException("Job history not found."));
        jobHistoryMapper.updateEntityFromUpdateModel(jobHistory, updateJobHistoryModel);
        return jobHistoryMapper.toModel(jobHistoryRepository.save(jobHistory));
    }

    @Transactional
    public void deleteJobHistoryById(@NotBlank String id) {
        jobHistoryRepository.deleteById(id);
    }
}
