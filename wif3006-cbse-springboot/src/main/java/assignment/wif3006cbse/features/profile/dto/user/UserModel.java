package assignment.wif3006cbse.features.profile.dto.user;

import java.util.List;

public record UserModel(
    String id,
    String email,
    String name,
    String about,
    String location,
    List<String> categories,
    List<String> skills,
    boolean isProfilePublic
) {

}
