package assignment.wif3006cbse.features.project.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record UpdateProjectModel(
        @NotNull String id,
        @NotNull String postedBy,
        byte[] companyLogo,
        String companyName,
        @NotBlank String projectTitle,
        @NotBlank String projectDescription,
        @NotBlank String location,
        @NotBlank String projectCategory,
        @NotBlank String projectDuration,
        @NotNull List<String> filter,
        @NotBlank String contactInformation,
        String additionalNotes,
        @NotNull Date deadline,
        @NotNull Double projectBudget,
        @NotNull List<String> requiredSkills,
        @NotNull Boolean agreedToTerms,
        Boolean posted,
        Boolean taken,
        Boolean completed,
        List<String> applicants,
        String serviceProvider,
        Boolean fileAccepted) {
}