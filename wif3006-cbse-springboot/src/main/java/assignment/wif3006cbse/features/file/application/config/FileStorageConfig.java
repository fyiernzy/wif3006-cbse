package assignment.wif3006cbse.features.file.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class FileStorageConfig {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Bean
    public Path uploadPath() {
        Path path = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(path);
        } catch (Exception ex) {
            throw new IllegalStateException("Could not create upload directory", ex);
        }
        return path;
    }
}
