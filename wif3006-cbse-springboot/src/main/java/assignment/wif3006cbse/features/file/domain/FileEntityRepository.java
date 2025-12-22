package assignment.wif3006cbse.features.file.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FileEntityRepository extends JpaRepository<FileEntity, String> {
    Optional<FileEntity> findByOriginalFileName(String originalFileName);

    List<FileEntity> findByIdIn(Set<String> ids);
}
