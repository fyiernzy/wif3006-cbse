package assignment.wif3006cbse.features.sample.mapper;

import assignment.wif3006cbse.config.MapStructConfig;
import assignment.wif3006cbse.features.sample.domain.Sample;
import assignment.wif3006cbse.features.sample.dto.CreateSampleModel;
import assignment.wif3006cbse.features.sample.dto.SampleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class)
public interface SampleMapper {

    SampleModel toModel(Sample sample);

    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Sample toEntity(CreateSampleModel createSampleModel);
}
