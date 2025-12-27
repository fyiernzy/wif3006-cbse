package assignment.wif3006cbse.features.project.controller;

import assignment.wif3006cbse.features.project.dto.project.CreateProjectModel;
import assignment.wif3006cbse.features.project.dto.project.ProjectModel;
import assignment.wif3006cbse.features.project.dto.project.UpdateProjectModel;
import assignment.wif3006cbse.features.project.dto.project.ProjectUserRequest;
import assignment.wif3006cbse.features.project.service.ProjectService;
import assignment.wif3006cbse.features.user.dto.UserModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ProjectModel createProject(@RequestBody @Valid CreateProjectModel createProjectModel) {
        return projectService.createProject(createProjectModel);
    }

    @GetMapping
    public List<ProjectModel> getAllProjects(@RequestParam(required = false) String q) {
        return projectService.getAllProjects(q);
    }

    @GetMapping("/{id}")
    public ProjectModel findProjectById(@PathVariable String id) {
        return projectService.findProjectById(id);
    }

    @PutMapping
    public ProjectModel updateProject(@RequestBody @Valid UpdateProjectModel updateProjectModel) {
        return projectService.updateProject(updateProjectModel);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable String id) {
        projectService.deleteProjectById(id);
    }

    @PostMapping("/favorite-project")
    public UserModel saveFavoriteProject(@RequestBody @Valid ProjectUserRequest request) {
        return projectService.saveFavoriteProject(request.userId(), request.projectId());
    }

    @GetMapping("/favorite-project/{userId}")
    public List<ProjectModel> getFavoriteProjects(@PathVariable String userId) {
        return projectService.getFavoriteProjects(userId);
    }

    @PostMapping("/remove-favorite-project")
    public UserModel removeFavoriteProject(@RequestBody @Valid ProjectUserRequest request) {
        return projectService.removeFavoriteProject(request.userId(), request.projectId());
    }

    @PostMapping("/applying-project")
    public void addApplyingProject(@RequestBody @Valid ProjectUserRequest request) {
        projectService.addApplyingProject(request.userId(), request.projectId());
    }

    @PutMapping("/applying-project/remove")
    public void removeApplyingProject(@RequestBody @Valid ProjectUserRequest request) {
        projectService.removeApplyingProject(request.userId(), request.projectId());
    }

    @GetMapping("/applying-project/{userId}")
    public List<ProjectModel> getApplyingProjects(@PathVariable String userId) {
        return projectService.getApplyingProjects(userId);
    }

    @PostMapping("/taken-project")
    public UserModel saveTakenProject(@RequestBody @Valid ProjectUserRequest request) {
        return projectService.saveTakenProject(request.userId(), request.projectId());
    }

    @GetMapping("/taken-project/{userId}")
    public List<ProjectModel> getTakenProjects(@PathVariable String userId) {
        return projectService.getTakenProjects(userId);
    }

    @PostMapping("/completed-project")
    public UserModel saveCompletedProject(@RequestBody @Valid ProjectUserRequest request) {
        return projectService.saveCompletedProject(request.userId(), request.projectId());
    }

    @GetMapping("/completed-project/{userId}")
    public List<ProjectModel> getCompletedProjects(@PathVariable String userId) {
        return projectService.getCompletedProjects(userId);
    }

    @PostMapping(value = "/upload-works", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProjectModel uploadCompletedWorks(
            @RequestParam("userId") String userId,
            @RequestParam("projectId") String projectId,
            @RequestParam("files") MultipartFile[] files) {
        return projectService.uploadCompletedWorks(userId, projectId, files);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileId) {
        Resource resource = projectService.downloadFile(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}