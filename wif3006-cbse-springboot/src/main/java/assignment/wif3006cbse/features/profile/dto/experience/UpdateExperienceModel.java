package assignment.wif3006cbse.features.profile.dto.experience;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UpdateExperienceModel(
    @NotBlank String id,
    String jobTitle,
    String company,
    String location,
    LocalDate startDate,
    LocalDate endDate,
    Boolean isCurrent,
    String description
) {

}
