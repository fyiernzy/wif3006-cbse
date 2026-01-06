package assignment.wif3006cbse.features.profile.application.dto.experience;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UpdateExperienceModel(
    String id,
    String title,
    String company,
    String description,
    LocalDate startDate,
    LocalDate endDate,
    boolean isCurrent
) {

}
