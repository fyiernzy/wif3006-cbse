package assignment.wif3006cbse.features.project.domain.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * UploadedFile entity representing a deliverable file uploaded by a freelancer.
 * Corresponds to the UploadedFiles concept managed by UploadedFilesDAO (Depth 1).
 */
public class UploadedFile implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String projectId;
    private String uploadedBy; // User ID of the freelancer who uploaded
    private String fileName;
    private String fileUrl;
    private String originalFileName;
    private String contentType;
    private long fileSize;
    private LocalDateTime submittedAt;

    public UploadedFile() {
        this.id = UUID.randomUUID().toString();
        this.submittedAt = LocalDateTime.now();
    }

    public UploadedFile(String projectId, String uploadedBy, String fileName, 
                        String fileUrl, String originalFileName, String contentType, long fileSize) {
        this();
        this.projectId = projectId;
        this.uploadedBy = uploadedBy;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.originalFileName = originalFileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
