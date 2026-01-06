package assignment.wif3006cbse.features.user.dto;

import assignment.wif3006cbse.features.user.domain.UserRole;

import java.util.List;

public record UserModel(
        String id,
        String username,
        String firstName,
        String lastName,
        String email,
        UserRole role,
        byte[] profilePic,
        String headline,
        String city,
        String state,
        List<String> categories,
        String university,
        List<String> skills,
        String about,
        String rating,
        List<String> favoriteProjects,
        List<String> takenProjects,
        List<String> applyingProjects,
        List<String> completedProjects,
        List<String> postedProjects,
        List<String> experienceIds,
        List<String> productIds) {

}
