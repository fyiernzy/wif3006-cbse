package assignment.wif3006cbse.features.sample.dto;

import assignment.wif3006cbse.features.sample.domain.SampleStatusEnum;
import lombok.With;

@With
public record SampleModel(
    Long id,
    String value,
    SampleStatusEnum sampleStatusEnum
) {

}
