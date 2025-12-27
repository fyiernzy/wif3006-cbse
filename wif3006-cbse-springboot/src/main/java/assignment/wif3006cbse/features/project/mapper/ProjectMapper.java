package assignment.wif3006cbse.features.project.mapper;

import assignment.wif3006cbse.config.MapStructConfig;
import assignment.wif3006cbse.features.project.domain.entity.Project;
import assignment.wif3006cbse.features.project.dto.project.CreateProjectModel;
import assignment.wif3006cbse.features.project.dto.project.ProjectModel;
import assignment.wif3006cbse.features.project.dto.project.UpdateProjectModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface ProjectMapper {

    ProjectModel toModel(Project project);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "uploadedFileIds", ignore = true)
    Project toEntity(CreateProjectModel createProjectModel);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "uploadedFileIds", ignore = true)
    void updateEntityFromUpdateModel(@MappingTarget Project project, UpdateProjectModel updateProjectModel);
}