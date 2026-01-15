package assignment.wif3006cbse.features.profile.dto.jobhistory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CreateJobHistoryModel(
    @NotBlank String userId,
    @NotBlank String projectId,
    @NotBlank String projectName,
    @NotBlank String role,
    @NotNull LocalDate startDate,
    LocalDate endDate,
    String status,
    Integer rating,
    String review,
    List<String> deliverables
) {
}
