package assignment.wif3006cbse.features.community.mapper;

import assignment.wif3006cbse.config.MapStructConfig;
import assignment.wif3006cbse.features.community.domain.entity.Post;
import assignment.wif3006cbse.features.community.dto.post.CreatePostModel;
import assignment.wif3006cbse.features.community.dto.post.PostModel;
import assignment.wif3006cbse.features.community.dto.post.UpdatePostModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface PostMapper {

    PostModel toModel(Post post);

    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Post toEntity(CreatePostModel createPostModel);

    @Mapping(target = "authorId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromUpdateModel(@MappingTarget Post post, UpdatePostModel updatePostModel);
}
