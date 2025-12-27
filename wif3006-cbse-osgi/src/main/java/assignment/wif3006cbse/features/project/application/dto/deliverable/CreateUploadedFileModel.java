package assignment.wif3006cbse.features.project.application.dto.deliverable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * DTO for creating/uploading a deliverable file.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record CreateUploadedFileModel(
    String projectId,
    String uploadedBy,
    String fileName,
    String fileUrl,
    String originalFileName,
    String contentType,
    long fileSize
) {}
