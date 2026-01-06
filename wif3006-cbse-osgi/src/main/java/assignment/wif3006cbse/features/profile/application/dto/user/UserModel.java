package assignment.wif3006cbse.features.profile.application.dto.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UserModel(
    String id,
    String email,
    String name,
    String about,
    String location,
    List<String> categories,
    List<String> skills,
    boolean isProfilePublic,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
