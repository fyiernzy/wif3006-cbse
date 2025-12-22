package assignment.wif3006cbse.features.file.application.mapper;

import assignment.wif3006cbse.config.MapStructConfig;
import assignment.wif3006cbse.features.file.application.dto.FileModel;
import assignment.wif3006cbse.features.file.domain.FileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class)
public interface FileEntityMapper {

    @Mapping(target = "fileName", source = "originalFileName")
    FileModel toModel(FileEntity fileEntity);
}
