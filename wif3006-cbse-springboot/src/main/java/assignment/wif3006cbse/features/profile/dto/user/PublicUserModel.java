package assignment.wif3006cbse.features.profile.dto.user;

import java.util.List;

public record PublicUserModel(
    String id,
    String name,
    String about,
    String location,
    List<String> categories,
    List<String> skills
) {

}
