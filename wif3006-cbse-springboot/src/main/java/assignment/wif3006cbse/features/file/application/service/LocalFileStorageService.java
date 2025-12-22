package assignment.wif3006cbse.features.file.application.service;


import assignment.wif3006cbse.features.file.application.dto.FileModel;
import assignment.wif3006cbse.features.file.application.mapper.FileEntityMapper;
import assignment.wif3006cbse.features.file.domain.FileEntity;
import assignment.wif3006cbse.features.file.domain.FileEntityRepository;
import assignment.wif3006cbse.utils.Asserts;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocalFileStorageService implements FileStorageService {

    private final Path uploadPath;
    private final FileEntityRepository fileRepository;
    private final FileEntityMapper fileEntityMapper;

    @Override
    @Transactional
    public FileModel store(MultipartFile file) {
        // 1. Validate if the file is valid
        validate(file);

        // 2. Save the file
        String originalFileName = file.getOriginalFilename();
        String cleanedName = FilenameUtils.getName(originalFileName);
        String extension = "." + FilenameUtils.getExtension(cleanedName);
        String storedFileName = UUID.randomUUID() + extension;

        Path targetPath = uploadPath.resolve(storedFileName).normalize();

        try {
            Files.createDirectories(uploadPath);
            Files.copy(file.getInputStream(), targetPath);
        } catch (IOException exception) {
            throw new IllegalStateException("Could not store file " + cleanedName, exception);
        }

        // 3. Save the file metadata to the database
        FileEntity fileEntity = mapToFileEntity(file, cleanedName,
            storedFileName, targetPath);
        return fileEntityMapper.toModel(fileRepository.save(fileEntity));
    }

    @Override
    @Transactional(readOnly = true)
    public FileModel findById(String id) {
        return fileRepository.findById(id)
            .map(fileEntityMapper::toModel)
            .orElseThrow(() ->
                new EntityNotFoundException("The file with id %s is not found!".formatted(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public FileModel findByFileName(String fileName) {
        return fileRepository.findByOriginalFileName(fileName)
            .map(fileEntityMapper::toModel)
            .orElseThrow(() ->
                new EntityNotFoundException(
                    "The file with fileName %s is not found!".formatted(fileName)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FileModel> findByIds(Set<String> ids) {
        return fileRepository.findByIdIn(ids).stream().map(fileEntityMapper::toModel).toList();
    }

    @Override
    public Resource getById(String id) {
        FileEntity fileEntity = fileRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("File not found"));

        Path filePath = Paths.get(fileEntity.getStoragePath());
        return new FileSystemResource(filePath);
    }

    private void validate(MultipartFile file) {
        Asserts.state(!file.isEmpty(), "File is empty");
        Asserts.notBlank(file.getOriginalFilename(), "File name");

        Tika tika = new Tika();
        String mimeType = tika.detect(file.getOriginalFilename());
        boolean isExecutableFile = "application/x-msdownload".equals(mimeType);
        Asserts.state(!isExecutableFile, "Executable files are not allowed");
    }

    private @NotNull FileEntity mapToFileEntity(MultipartFile file,
                                                String cleanedFileName,
                                                String storedFileName,
                                                Path targetPath) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setOriginalFileName(cleanedFileName);
        fileEntity.setStoredFileName(storedFileName);
        fileEntity.setContentType(
            file.getContentType() != null ? file.getContentType() : "application/octet-stream");
        fileEntity.setFileSizeBytes(file.getSize());
        fileEntity.setStoragePath(targetPath.toAbsolutePath().toString());
        fileEntity.setCreatedAt(LocalDateTime.now());
        return fileEntity;
    }
}
