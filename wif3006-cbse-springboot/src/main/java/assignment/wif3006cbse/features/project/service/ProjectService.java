package assignment.wif3006cbse.features.project.service;

import assignment.wif3006cbse.features.project.domain.entity.Project;
import assignment.wif3006cbse.features.project.domain.repository.ProjectRepository;
import assignment.wif3006cbse.features.project.dto.project.CreateProjectModel;
import assignment.wif3006cbse.features.project.dto.project.ProjectModel;
import assignment.wif3006cbse.features.project.dto.project.UpdateProjectModel;
import assignment.wif3006cbse.features.project.mapper.ProjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import assignment.wif3006cbse.features.user.domain.User;
import assignment.wif3006cbse.features.user.domain.UserRepository;
import assignment.wif3006cbse.features.user.dto.UserModel;
import assignment.wif3006cbse.features.user.mapper.UserMapper;
import assignment.wif3006cbse.features.file.application.dto.FileModel;
import assignment.wif3006cbse.features.file.application.service.LocalFileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final LocalFileStorageService fileStorageService;

    @Transactional
    public ProjectModel createProject(@Valid CreateProjectModel createProjectModel) {
        Project project = projectMapper.toEntity(createProjectModel);
        return projectMapper.toModel(projectRepository.save(project));
    }

    @Transactional(readOnly = true)
    public ProjectModel findProjectById(@NotNull String id) {
        return projectMapper.toModel(projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found.")));
    }

    @Transactional
    public ProjectModel updateProject(@Valid UpdateProjectModel updateProjectModel) {
        Project project = projectRepository.findById(updateProjectModel.id())
                .orElseThrow(() -> new EntityNotFoundException("Project not found."));
        projectMapper.updateEntityFromUpdateModel(project, updateProjectModel);
        return projectMapper.toModel(projectRepository.save(project));
    }

    @Transactional
    public void deleteProjectById(@NotNull String id) {
        projectRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ProjectModel> getAllProjects(String query) {
        if (query != null && !query.isBlank()) {
            return projectRepository.findAll().stream()
                    .filter(p -> p.getProjectTitle().toLowerCase().contains(query.toLowerCase()))
                    .map(projectMapper::toModel)
                    .collect(Collectors.toList());
        }
        return projectRepository.findAll().stream()
                .filter(p -> p.getTaken() == null || !p.getTaken())
                .map(projectMapper::toModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserModel saveFavoriteProject(String userId, String projectId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (user.getFavoriteProjects() == null) {
            user.setFavoriteProjects(new ArrayList<>());
        }
        if (!user.getFavoriteProjects().contains(projectId)) {
            user.getFavoriteProjects().add(projectId);
            userRepository.save(user);
        }
        return userMapper.toModel(user);
    }

    @Transactional
    public UserModel removeFavoriteProject(String userId, String projectId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (user.getFavoriteProjects() != null) {
            user.getFavoriteProjects().remove(projectId);
            userRepository.save(user);
        }
        return userMapper.toModel(user);
    }

    @Transactional(readOnly = true)
    public List<ProjectModel> getFavoriteProjects(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<String> favoriteIds = user.getFavoriteProjects();
        if (favoriteIds == null || favoriteIds.isEmpty()) {
            return List.of();
        }
        return projectRepository.findAllById(favoriteIds).stream()
                .map(projectMapper::toModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addApplyingProject(String userId, String projectId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (user.getApplyingProjects() == null)
            user.setApplyingProjects(new ArrayList<>());
        if (!user.getApplyingProjects().contains(projectId)) {
            user.getApplyingProjects().add(projectId);
            userRepository.save(user);
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        if (project.getApplicants() == null)
            project.setApplicants(new ArrayList<>());
        if (!project.getApplicants().contains(userId)) {
            project.getApplicants().add(userId);
            projectRepository.save(project);
        }
        // Notification logic omitted as Notification system is not fully set up in
        // context
    }

    @Transactional
    public void removeApplyingProject(String userId, String projectId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (user.getApplyingProjects() != null) {
            user.getApplyingProjects().remove(projectId);
            userRepository.save(user);
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
        if (project.getApplicants() != null) {
            project.getApplicants().remove(userId);
            projectRepository.save(project);
        }
    }

    @Transactional(readOnly = true)
    public List<ProjectModel> getApplyingProjects(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<String> projectIds = user.getApplyingProjects() != null ? user.getApplyingProjects() : List.of();
        return projectRepository.findAllById(projectIds).stream()
                .map(projectMapper::toModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserModel saveTakenProject(String userId, String projectId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (user.getTakenProjects() == null)
            user.setTakenProjects(new ArrayList<>());
        if (!user.getTakenProjects().contains(projectId)) {
            user.getTakenProjects().add(projectId);
            userRepository.save(user);
        }
        return userMapper.toModel(user);
    }

    @Transactional(readOnly = true)
    public List<ProjectModel> getTakenProjects(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<String> projectIds = user.getTakenProjects() != null ? user.getTakenProjects() : List.of();
        return projectRepository.findAllById(projectIds).stream()
                .map(projectMapper::toModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserModel saveCompletedProject(String userId, String projectId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (user.getCompletedProjects() == null)
            user.setCompletedProjects(new ArrayList<>());
        if (!user.getCompletedProjects().contains(projectId)) {
            user.getCompletedProjects().add(projectId);
            userRepository.save(user);
        }
        return userMapper.toModel(user);
    }

    @Transactional(readOnly = true)
    public List<ProjectModel> getCompletedProjects(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<String> projectIds = user.getCompletedProjects() != null ? user.getCompletedProjects() : List.of();
        return projectRepository.findAllById(projectIds).stream()
                .map(projectMapper::toModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProjectModel uploadCompletedWorks(String userId, String projectId, MultipartFile[] files) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        List<String> uploadedIds = new ArrayList<>();
        if (project.getUploadedFileIds() != null) {
            uploadedIds.addAll(project.getUploadedFileIds());
        }

        for (MultipartFile file : files) {
            FileModel fileModel = fileStorageService.store(file);
            uploadedIds.add(fileModel.id());
        }

        project.setUploadedFileIds(uploadedIds);
        project.setServiceProvider(userId);
        Project savedProject = projectRepository.save(project);

        // Logic from js involves updating user's taken/completed projects if needed,
        // but explicit call in js was commented out or partial. Focusing on project
        // update.

        return projectMapper.toModel(savedProject);
    }

    public Resource downloadProjectFiles(String projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        List<String> fileIds = project.getUploadedFileIds();
        if (fileIds == null || fileIds.isEmpty()) {
            throw new EntityNotFoundException("No files found for this project.");
        }

        try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
                java.util.zip.ZipOutputStream zos = new java.util.zip.ZipOutputStream(baos)) {

            for (String fileId : fileIds) {
                FileModel fileModel = fileStorageService.findById(fileId);
                Resource resource = fileStorageService.getById(fileId);

                java.util.zip.ZipEntry entry = new java.util.zip.ZipEntry(fileModel.fileName());
                entry.setSize(fileModel.fileSizeBytes());
                zos.putNextEntry(entry);

                java.io.InputStream is = resource.getInputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = is.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                is.close();
                zos.closeEntry();
            }
            zos.finish();
            return new org.springframework.core.io.ByteArrayResource(baos.toByteArray());
        } catch (java.io.IOException e) {
            throw new RuntimeException("Error while zipping files", e);
        }
    }
}
