package assignment.wif3006cbse.features.sample.dto;

import assignment.wif3006cbse.features.sample.domain.SampleStatusEnum;

public record SampleModel(
    Long id,
    String sampleValue,
    SampleStatusEnum sampleStatusEnum
) {

}
