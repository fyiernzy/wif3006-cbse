package assignment.wif3006cbse.features.profile.mapper;

import assignment.wif3006cbse.config.MapStructConfig;
import assignment.wif3006cbse.features.profile.domain.entity.Experience;
import assignment.wif3006cbse.features.profile.dto.experience.CreateExperienceModel;
import assignment.wif3006cbse.features.profile.dto.experience.ExperienceModel;
import assignment.wif3006cbse.features.profile.dto.experience.UpdateExperienceModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface ExperienceMapper {

    @Mapping(source = "current", target = "isCurrent")
    ExperienceModel toModel(Experience experience);

    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(source = "isCurrent", target = "current")
    Experience toEntity(CreateExperienceModel createExperienceModel);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(source = "isCurrent", target = "current")
    void updateEntityFromUpdateModel(@MappingTarget Experience experience, UpdateExperienceModel updateExperienceModel);
}
