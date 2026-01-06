package assignment.wif3006cbse.features.profile.controller;

import assignment.wif3006cbse.features.profile.dto.jobhistory.JobHistoryModel;
import assignment.wif3006cbse.features.profile.service.JobHistoryService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile/job-history")
public class JobHistoryController {

    private final JobHistoryService jobHistoryService;

    @GetMapping("/{id}")
    public JobHistoryModel findJobHistoryById(@PathVariable @NotBlank String id) {
        return jobHistoryService.findJobHistoryById(id);
    }

    @GetMapping("/user/{userId}")
    public List<JobHistoryModel> findAllByUserId(@PathVariable @NotBlank String userId) {
        return jobHistoryService.findAllByUserId(userId);
    }

    @GetMapping("/user/{userId}/paged")
    public PagedModel<JobHistoryModel> findAllByUserIdPaged(@PathVariable @NotBlank String userId,
                                                            @PageableDefault Pageable pageable) {
        return new PagedModel<>(jobHistoryService.findAllByUserId(userId, pageable));
    }

    @GetMapping("/status/{status}")
    public List<JobHistoryModel> findAllByStatus(@PathVariable @NotBlank String status) {
        return jobHistoryService.findAllByStatus(status);
    }
}
