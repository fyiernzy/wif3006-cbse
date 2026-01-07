package assignment.wif3006cbse.shared.file;

import java.io.File;
import java.io.InputStream;

public interface FileStorageService {
    String store(InputStream content, String originalFilename);

    File load(String storedFilename);

    void delete(String storedFilename);

    File loadById(String fileId);
}
