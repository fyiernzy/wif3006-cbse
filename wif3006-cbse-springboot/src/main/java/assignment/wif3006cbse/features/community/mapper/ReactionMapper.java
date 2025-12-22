package assignment.wif3006cbse.features.community.mapper;

import assignment.wif3006cbse.config.MapStructConfig;
import assignment.wif3006cbse.features.community.domain.entity.Reaction;
import assignment.wif3006cbse.features.community.dto.reaction.CreateReactionModel;
import assignment.wif3006cbse.features.community.dto.reaction.ReactionModel;
import assignment.wif3006cbse.features.community.dto.reaction.UpdateReactionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface ReactionMapper {

    ReactionModel toModel(Reaction reaction);

    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Reaction toEntity(CreateReactionModel createReactionModel);

    @Mapping(target = "sourceType", ignore = true)
    @Mapping(target = "sourceId", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "authorId", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromUpdateModel(@MappingTarget Reaction reaction,
                                     UpdateReactionModel updateReactionModel);
}
