package assignment.wif3006cbse.features.profile.dto.experience;

import java.time.LocalDate;

public record ExperienceModel(
    String id,
    String userId,
    String jobTitle,
    String company,
    String location,
    LocalDate startDate,
    LocalDate endDate,
    boolean isCurrent,
    String description
) {

}
