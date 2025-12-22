package assignment.wif3006cbse.features.community.mapper;

import assignment.wif3006cbse.config.MapStructConfig;
import assignment.wif3006cbse.features.community.domain.entity.Comment;
import assignment.wif3006cbse.features.community.dto.comment.CommentModel;
import assignment.wif3006cbse.features.community.dto.comment.CreateCommentModel;
import assignment.wif3006cbse.features.community.dto.comment.UpdateCommentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface CommentMapper {

    CommentModel toModel(Comment comment);

    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Comment toEntity(CreateCommentModel createCommentModel);

    @Mapping(target = "threadId", ignore = true)
    @Mapping(target = "authorId", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromUpdateModel(@MappingTarget Comment comment,
                                     UpdateCommentModel updateCommentModel);
}
