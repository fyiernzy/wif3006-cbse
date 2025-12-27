package assignment.wif3006cbse.features.project.application.service;

import assignment.wif3006cbse.features.project.application.dto.deliverable.CreateUploadedFileModel;
import assignment.wif3006cbse.features.project.application.dto.deliverable.UploadedFileModel;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for Deliverable/UploadedFile operations.
 * Dependency Depth 3 - depends on ProjectDAO, UploadedFilesDAO, NotificationService.
 * Handles file upload/download for project deliverables.
 */
public interface DeliverableService {

    /**
     * Upload a deliverable file for a project.
     */
    UploadedFileModel uploadFile(CreateUploadedFileModel model);

    /**
     * Get uploaded file by ID.
     */
    Optional<UploadedFileModel> getFileById(String fileId);

    /**
     * Get all uploaded files for a project.
     */
    List<UploadedFileModel> getFilesByProjectId(String projectId);

    /**
     * Get all files uploaded by a specific user.
     */
    List<UploadedFileModel> getFilesByUploadedBy(String userId);

    /**
     * Delete an uploaded file.
     */
    boolean deleteFile(String fileId);

    /**
     * Delete all files for a project.
     */
    void deleteFilesByProjectId(String projectId);

    /**
     * Submit deliverables for review (notifies project owner).
     */
    void submitDeliverablesForReview(String projectId, String submitterId);

    /**
     * Accept deliverables (marks project files as accepted).
     */
    void acceptDeliverables(String projectId, String reviewerId);

    /**
     * Reject deliverables (notifies freelancer).
     */
    void rejectDeliverables(String projectId, String reviewerId, String reason);
}
