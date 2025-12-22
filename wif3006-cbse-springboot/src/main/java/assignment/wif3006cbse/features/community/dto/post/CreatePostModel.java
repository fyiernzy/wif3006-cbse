package assignment.wif3006cbse.features.community.dto.post;

import jakarta.validation.constraints.NotBlank;

public record CreatePostModel(
    @NotBlank String authorId,
    String title,
    @NotBlank String content
) {

}
