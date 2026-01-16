package assignment.wif3006cbse.features.community.application.dto.reaction;

import assignment.wif3006cbse.features.community.domain.vo.ReactionSourceTypeEnum;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record CreateReactionModel(
    @JsonProperty("sourceType") ReactionSourceTypeEnum sourceType,
    @JsonProperty("sourceId") String sourceId,
    @JsonProperty("authorId") String authorId,
    @JsonProperty("reaction") String reaction
) {

}
