package assignment.wif3006cbse.features.community.mapper;

import assignment.wif3006cbse.config.MapStructConfig;
import assignment.wif3006cbse.features.community.domain.entity.ThreadEntity;
import assignment.wif3006cbse.features.community.dto.thread.CreateThreadEntityModel;
import assignment.wif3006cbse.features.community.dto.thread.ThreadEntityModel;
import assignment.wif3006cbse.features.community.dto.thread.UpdateThreadEntityModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface ThreadEntityMapper {

    ThreadEntityModel toModel(ThreadEntity threadEntity);

    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    ThreadEntity toEntity(CreateThreadEntityModel createThreadEntityModel);


    @Mapping(target = "postId", ignore = true)
    @Mapping(target = "authorId", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromUpdateModel(@MappingTarget ThreadEntity threadEntity,
                                     UpdateThreadEntityModel updateThreadEntityModel);
}
