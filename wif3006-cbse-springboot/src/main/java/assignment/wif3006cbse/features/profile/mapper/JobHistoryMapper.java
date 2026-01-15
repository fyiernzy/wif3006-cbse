package assignment.wif3006cbse.features.profile.mapper;

import assignment.wif3006cbse.config.MapStructConfig;
import assignment.wif3006cbse.features.profile.domain.entity.JobHistory;
import assignment.wif3006cbse.features.profile.dto.jobhistory.CreateJobHistoryModel;
import assignment.wif3006cbse.features.profile.dto.jobhistory.JobHistoryModel;
import assignment.wif3006cbse.features.profile.dto.jobhistory.UpdateJobHistoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface JobHistoryMapper {

    JobHistoryModel toModel(JobHistory jobHistory);
    
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    JobHistory toEntity(CreateJobHistoryModel createJobHistoryModel);
    
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "projectId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromUpdateModel(@MappingTarget JobHistory jobHistory, UpdateJobHistoryModel updateJobHistoryModel);
}
