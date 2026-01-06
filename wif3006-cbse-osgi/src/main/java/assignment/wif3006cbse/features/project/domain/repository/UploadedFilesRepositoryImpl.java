package assignment.wif3006cbse.features.project.domain.repository;

import assignment.wif3006cbse.features.project.domain.entity.UploadedFile;
import assignment.wif3006cbse.shared.FileBasedRepository;
import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * File-based implementation of UploadedFilesRepository.
 * Corresponds to UploadedFilesDAO (Depth 1).
 */
@Component(service = UploadedFilesRepository.class)
public class UploadedFilesRepositoryImpl extends FileBasedRepository<UploadedFile, String> implements UploadedFilesRepository {

    public UploadedFilesRepositoryImpl() {
        super("uploaded_files.json", UploadedFile::getId);
    }

    @Override
    public List<UploadedFile> findByProjectId(String projectId) {
        return findAll().stream()
                .filter(f -> projectId.equals(f.getProjectId()))
                .toList();
    }

    @Override
    public List<UploadedFile> findByUploadedBy(String userId) {
        return findAll().stream()
                .filter(f -> userId.equals(f.getUploadedBy()))
                .toList();
    }

    @Override
    public List<UploadedFile> findByProjectIdAndUploadedBy(String projectId, String userId) {
        return findAll().stream()
                .filter(f -> projectId.equals(f.getProjectId()) && userId.equals(f.getUploadedBy()))
                .toList();
    }

    @Override
    public void deleteByProjectId(String projectId) {
        List<UploadedFile> files = findByProjectId(projectId);
        files.forEach(f -> deleteById(f.getId()));
    }
}
