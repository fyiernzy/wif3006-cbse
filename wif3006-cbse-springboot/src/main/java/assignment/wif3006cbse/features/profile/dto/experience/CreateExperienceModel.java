package assignment.wif3006cbse.features.profile.dto.experience;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateExperienceModel(
    @NotBlank String userId,
    @NotBlank String jobTitle,
    @NotBlank String company,
    String location,
    @NotNull LocalDate startDate,
    LocalDate endDate,
    Boolean isCurrent,
    String description
) {

}
