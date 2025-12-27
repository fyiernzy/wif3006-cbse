package assignment.wif3006cbse.features.project.application.dto.project;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for project details response.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record ProjectModel(
    String id,
    String postedBy,
    String companyName,
    String projectTitle,
    String projectDescription,
    String location,
    String projectCategory,
    String projectDuration,
    List<String> filters,
    List<String> requiredSkills,
    BigDecimal projectBudget,
    LocalDate deadline,
    String contactInformation,
    String additionalNotes,
    boolean agreedToTerms,
    boolean posted,
    boolean taken,
    boolean completed,
    boolean fileAccepted,
    String serviceProvider,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
