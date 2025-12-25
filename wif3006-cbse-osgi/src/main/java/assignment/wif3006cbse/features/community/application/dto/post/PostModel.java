package assignment.wif3006cbse.features.community.application.dto.post;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record PostModel(
    String id,
    String authorId,
    String title,
    String content,
    LocalDateTime createdAt
) {

}
