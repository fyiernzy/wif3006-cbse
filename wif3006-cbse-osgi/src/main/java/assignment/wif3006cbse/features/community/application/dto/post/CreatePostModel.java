package assignment.wif3006cbse.features.community.application.dto.post;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record CreatePostModel(
    String authorId,
    String title,
    String content
) {

}
