package assignment.wif3006cbse.features.community.dto.post;

import jakarta.validation.constraints.NotBlank;

public record UpdatePostModel(
    @NotBlank String id,
    String title,
    @NotBlank String content
) {

}
