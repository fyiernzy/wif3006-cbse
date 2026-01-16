package assignment.wif3006cbse.features.community.application.dto.post;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UpdatePostModel(
    String id,
    String title,
    String content
) {

}
