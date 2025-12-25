package assignment.wif3006cbse.features.file.web;

import assignment.wif3006cbse.features.file.application.dto.FileModel;
import assignment.wif3006cbse.features.file.application.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileStorageService fileStorageService;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileModel uploadFile(@RequestPart("file") MultipartFile file) {
        return fileStorageService.store(file);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<FileModel> getFileById(@PathVariable String id) {
        FileModel fileModel = fileStorageService.findById(id);
        return ResponseEntity.ok(fileModel);
    }

    @GetMapping("/name/{fileName}")
    public FileModel getFileByName(@PathVariable String fileName) {
        return fileStorageService.findByFileName(fileName);
    }

    @GetMapping("/bulk/id")
    public List<FileModel> getFilesByIds(@RequestParam("ids") Set<String> ids) {
        return fileStorageService.findByIds(ids);
    }

    @GetMapping("/file/id/{id}")
    public ResponseEntity<Resource> download(@PathVariable String id) {
        FileModel fileModel = fileStorageService.findById(id);

        return ResponseEntity.ok()
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "inline; filename=\"" + fileModel.fileName() + "\""
            )
            .contentType(MediaType.parseMediaType(fileModel.contentType()))
            .body(fileStorageService.getById(fileModel.id()));
    }
}
