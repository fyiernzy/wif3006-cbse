package assignment.wif3006cbse.features.file.application.mapper;

import assignment.wif3006cbse.features.file.application.dto.FileModel;
import assignment.wif3006cbse.features.file.domain.FileEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-03T17:31:40+0800",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class FileEntityMapperImpl implements FileEntityMapper {

    @Override
    public FileModel toModel(FileEntity fileEntity) {
        if ( fileEntity == null ) {
            return null;
        }

        String fileName = null;
        String id = null;
        String contentType = null;
        Long fileSizeBytes = null;

        if ( fileEntity.getOriginalFileName() != null ) {
            fileName = fileEntity.getOriginalFileName();
        }
        if ( fileEntity.getId() != null ) {
            id = fileEntity.getId();
        }
        if ( fileEntity.getContentType() != null ) {
            contentType = fileEntity.getContentType();
        }
        if ( fileEntity.getFileSizeBytes() != null ) {
            fileSizeBytes = fileEntity.getFileSizeBytes();
        }

        FileModel fileModel = new FileModel( id, fileName, contentType, fileSizeBytes );

        return fileModel;
    }
}
