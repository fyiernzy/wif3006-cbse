package assignment.wif3006cbse.features.community.application.dto.comment;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record CommentModel(
    String id,
    String threadId,
    String authorId,
    String content
) {

}
