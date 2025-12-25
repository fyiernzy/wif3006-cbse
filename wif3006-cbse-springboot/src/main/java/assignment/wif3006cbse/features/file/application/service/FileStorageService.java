package assignment.wif3006cbse.features.file.application.service;

import assignment.wif3006cbse.features.file.application.dto.FileModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Validated
public interface FileStorageService {

    FileModel store(@NotNull MultipartFile file);

    FileModel findById(@NotBlank String id);

    FileModel findByFileName(@NotBlank String fileName);

    List<FileModel> findByIds(@NotEmpty Set<String> ids);

    Resource getById(@NotBlank String id);
}
