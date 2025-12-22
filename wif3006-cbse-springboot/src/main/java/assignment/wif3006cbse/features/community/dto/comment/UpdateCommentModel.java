package assignment.wif3006cbse.features.community.dto.comment;

import jakarta.validation.constraints.NotBlank;

public record UpdateCommentModel(
    @NotBlank String id,
    @NotBlank String content
) {

}
