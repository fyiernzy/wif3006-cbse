package assignment.wif3006cbse.features.community.dto.reaction;

import assignment.wif3006cbse.features.community.domain.vo.ReactionSourceTypeEnum;

public record ReactionModel(
    String id,
    ReactionSourceTypeEnum sourceType,
    String sourceId,
    String authorId,
    String reaction
) {

}
