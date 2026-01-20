package assignment.wif3006cbse.features.profile.application.dto.experience;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record ExperienceModel(
    String id,
    String userId,
    String title,
    String company,
    String description,
    LocalDate startDate,
    LocalDate endDate,
    boolean isCurrent,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
