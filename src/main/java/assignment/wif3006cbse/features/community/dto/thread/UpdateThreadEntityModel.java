package assignment.wif3006cbse.features.community.dto.thread;

import jakarta.validation.constraints.NotBlank;

public record UpdateThreadEntityModel(
    @NotBlank String id,
    @NotBlank String content
) {

}
