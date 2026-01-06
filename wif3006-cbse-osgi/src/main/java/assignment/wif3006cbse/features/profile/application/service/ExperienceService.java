package assignment.wif3006cbse.features.profile.application.service;

import assignment.wif3006cbse.features.profile.application.dto.experience.CreateExperienceModel;
import assignment.wif3006cbse.features.profile.application.dto.experience.ExperienceModel;
import assignment.wif3006cbse.features.profile.application.dto.experience.UpdateExperienceModel;

import java.util.List;

public interface ExperienceService {

    ExperienceModel createExperience(CreateExperienceModel createExperienceModel);

    ExperienceModel findExperienceById(String id);

    List<ExperienceModel> findAllByUserId(String userId);

    ExperienceModel updateExperience(UpdateExperienceModel updateExperienceModel);

    void deleteExperience(String id);
}
