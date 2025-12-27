package assignment.wif3006cbse.features.project.application.dto.project;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for project list item (summary view).
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record ProjectListModel(
    String id,
    String companyName,
    String projectTitle,
    String projectCategory,
    String projectDuration,
    String location,
    List<String> requiredSkills,
    BigDecimal projectBudget,
    LocalDate deadline,
    boolean taken,
    boolean completed
) {}
