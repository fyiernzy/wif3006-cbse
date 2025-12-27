package assignment.wif3006cbse.features.project.application.dto.deliverable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;

/**
 * DTO for uploaded file details response.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UploadedFileModel(
    String id,
    String projectId,
    String uploadedBy,
    String fileName,
    String fileUrl,
    String originalFileName,
    String contentType,
    long fileSize,
    LocalDateTime submittedAt
) {}
