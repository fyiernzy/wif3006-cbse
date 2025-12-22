package assignment.wif3006cbse.features.community.dto.reaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateReactionModel(
    @NotBlank String id,
    @NotBlank String reaction
) {

}
