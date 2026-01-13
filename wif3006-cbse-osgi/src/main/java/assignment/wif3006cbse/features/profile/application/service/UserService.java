package assignment.wif3006cbse.features.profile.application.service;

import assignment.wif3006cbse.features.profile.application.dto.user.*;

import java.util.List;

public interface UserService {

    UserModel createUser(CreateUserModel createUserModel);

    UserModel findUserById(String id);

    UserModel updateUser(UpdateUserModel updateUserModel);

    UserModel updateSkills(UpdateSkillsModel updateSkillsModel);

    UserModel updateVisibility(String userId, boolean isPublic);

    PublicUserModel getPublicProfile(String userId);

    UserModel addFavoriteProject(String userId, String projectId);

    UserModel removeFavoriteProject(String userId, String projectId);

    UserModel addApplyingProject(String userId, String projectId);

    UserModel removeApplyingProject(String userId, String projectId);

    void deleteUser(String id);

    List<UserModel> getAllUsers();
}
