package assignment.wif3006cbse.features.profile.mapper;

import assignment.wif3006cbse.config.MapStructConfig;
import assignment.wif3006cbse.features.profile.domain.entity.JobHistory;
import assignment.wif3006cbse.features.profile.dto.jobhistory.JobHistoryModel;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface JobHistoryMapper {

    JobHistoryModel toModel(JobHistory jobHistory);
}
