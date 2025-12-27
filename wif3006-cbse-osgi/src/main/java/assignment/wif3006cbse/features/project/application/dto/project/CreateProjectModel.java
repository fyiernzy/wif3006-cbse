package assignment.wif3006cbse.features.project.application.dto.project;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for creating a new project.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record CreateProjectModel(
    String postedBy,
    String companyName,
    String projectTitle,
    String projectDescription,
    String location,
    String projectCategory,
    String projectDuration,
    List<String> requiredSkills,
    BigDecimal projectBudget,
    LocalDate deadline,
    String contactInformation,
    String additionalNotes,
    boolean agreedToTerms
) {}
