package assignment.wif3006cbse.features.community.dto.reaction;

import assignment.wif3006cbse.features.community.domain.vo.ReactionSourceTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateReactionModel(
    @NotNull ReactionSourceTypeEnum sourceType,
    @NotBlank String sourceId,
    @NotBlank String authorId,
    @NotBlank String reaction
) {

}
