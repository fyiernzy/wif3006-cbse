package assignment.wif3006cbse.features.community.dto.thread;

import jakarta.validation.constraints.NotBlank;

public record CreateThreadEntityModel(
    @NotBlank String postId,
    @NotBlank String authorId,
    @NotBlank String content
) {

}
