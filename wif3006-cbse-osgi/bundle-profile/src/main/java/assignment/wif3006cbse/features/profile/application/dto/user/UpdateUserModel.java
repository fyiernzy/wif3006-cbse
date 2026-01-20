package assignment.wif3006cbse.features.profile.application.dto.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UpdateUserModel(
    String id,
    String name,
    String about,
    String location,
    List<String> categories
) {

}
