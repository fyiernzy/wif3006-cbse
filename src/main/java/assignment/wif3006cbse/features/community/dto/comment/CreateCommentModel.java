package assignment.wif3006cbse.features.community.dto.comment;

import jakarta.validation.constraints.NotBlank;

public record CreateCommentModel(
    @NotBlank String threadId,
    @NotBlank String authorId,
    @NotBlank String content
) {

}
