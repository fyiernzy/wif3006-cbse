package assignment.wif3006cbse.features.community.application.dto.thread;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record CreateThreadModel(
    String content,
    String authorId,
    String postId
) {

}
