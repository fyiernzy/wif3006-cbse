package assignment.wif3006cbse.shared.file;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

@Component(service = FileStorageService.class, immediate = true)
public class LocalFileStorageHelper implements FileStorageService {
    private final String uploadPath = System.getProperty("user.home") + "/.wif3006-cbse-data/uploads/";

    @Activate
    public void activate() {
        File directory = new File(uploadPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @Override
    public String store(InputStream content, String originalFilename) {
        String extension = "";
        int i = originalFilename.lastIndexOf('.');
        if (i > 0) {
            extension = originalFilename.substring(i);
        }

        String storedFilename = UUID.randomUUID().toString() + extension;
        Path targetLocation = Paths.get(uploadPath).resolve(storedFilename);
        try {
            Files.copy(content, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return storedFilename;
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + originalFilename, e);
        }
    }

    @Override
    public File load(String storedFilename) {
        return new File(uploadPath + storedFilename);
    }

    @Override
    public void delete(String storedFilename) {
        try {
            Files.deleteIfExists(Paths.get(uploadPath).resolve(storedFilename));
        } catch (IOException e) {
            // Log error
        }
    }

    @Override
    public File loadById(String fileId) {
        try (Stream<Path> stream = Files.list(Paths.get(uploadPath))) {
            return stream
                    .filter(path -> {
                        String name = path.getFileName().toString();
                        return name.equals(fileId) || name.startsWith(fileId + ".");
                    })
                    .findFirst()
                    .map(Path::toFile)
                    .orElse(null);
        } catch (IOException e) {
            return null;
        }
    }
}
