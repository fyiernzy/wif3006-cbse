package assignment.wif3006cbse.features.profile.dto.jobhistory;

import java.time.LocalDate;
import java.util.List;

public record JobHistoryModel(
    String id,
    String userId,
    String projectId,
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
