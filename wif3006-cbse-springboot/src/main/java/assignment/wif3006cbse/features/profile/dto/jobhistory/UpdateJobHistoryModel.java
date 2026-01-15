package assignment.wif3006cbse.features.profile.dto.jobhistory;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public record UpdateJobHistoryModel(
    @NotBlank String id,
    String projectName,
    String role,
    LocalDate startDate,
    LocalDate endDate,
    String status,
    Integer rating,
    String review,
    List<String> deliverables
) {
}
