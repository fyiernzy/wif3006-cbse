package assignment.wif3006cbse.features.profile.application.service;

import assignment.wif3006cbse.features.profile.application.dto.jobhistory.JobHistoryModel;

import java.util.List;

public interface JobHistoryService {

    JobHistoryModel findJobHistoryById(String id);

    List<JobHistoryModel> findAllByUserId(String userId);

    List<JobHistoryModel> findAllByStatus(String status);
}
