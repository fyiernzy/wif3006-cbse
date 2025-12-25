package assignment.wif3006cbse.features.community.application.dto.reaction;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UpdateReactionModel(
    @JsonProperty("id") String id,
    @JsonProperty("reaction") String reaction
) {

}
