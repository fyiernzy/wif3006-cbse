package assignment.wif3006cbse.features.project.domain.repository;

import assignment.wif3006cbse.features.project.domain.entity.UploadedFile;
import assignment.wif3006cbse.shared.spi.CrudRepository;

import java.util.List;

/**
 * Repository interface for UploadedFile entity (UploadedFilesDAO).
 * Dependency Depth 1 - depends on Project.
 */
public interface UploadedFilesRepository extends CrudRepository<UploadedFile, String> {

    /**
     * Find all uploaded files for a specific project.
     */
    List<UploadedFile> findByProjectId(String projectId);

    /**
     * Find all files uploaded by a specific user.
     */
    List<UploadedFile> findByUploadedBy(String userId);

    /**
     * Find files by project and uploader.
     */
    List<UploadedFile> findByProjectIdAndUploadedBy(String projectId, String userId);

    /**
     * Delete all files for a project.
     */
    void deleteByProjectId(String projectId);
}
