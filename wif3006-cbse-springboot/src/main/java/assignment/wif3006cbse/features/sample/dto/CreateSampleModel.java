package assignment.wif3006cbse.features.sample.dto;

import assignment.wif3006cbse.features.sample.domain.SampleStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSampleModel(
    @NotBlank String sampleValue,
    @NotNull SampleStatusEnum sampleStatusEnum
) {

}
