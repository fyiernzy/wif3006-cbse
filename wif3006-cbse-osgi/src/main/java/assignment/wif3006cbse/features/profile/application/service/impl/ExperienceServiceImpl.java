package assignment.wif3006cbse.features.profile.application.service.impl;

import assignment.wif3006cbse.features.profile.application.dto.experience.CreateExperienceModel;
import assignment.wif3006cbse.features.profile.application.dto.experience.ExperienceModel;
import assignment.wif3006cbse.features.profile.application.dto.experience.UpdateExperienceModel;
import assignment.wif3006cbse.features.profile.application.service.ExperienceService;
import assignment.wif3006cbse.features.profile.domain.entity.Experience;
import assignment.wif3006cbse.features.profile.domain.repository.ExperienceRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component(service = ExperienceService.class)
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;

    @Activate
    public ExperienceServiceImpl(@Reference ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public ExperienceServiceImpl() {
        this.experienceRepository = null;
    }

    @Override
    public ExperienceModel createExperience(CreateExperienceModel createExperienceModel) {
        Experience experience = new Experience(
            createExperienceModel.userId(),
            createExperienceModel.title(),
            createExperienceModel.company(),
            createExperienceModel.startDate()
        );
        experience.setDescription(createExperienceModel.description());
        experience.setEndDate(createExperienceModel.endDate());
        experience.setCurrent(createExperienceModel.isCurrent());

        Experience saved = experienceRepository.save(experience);
        return toModel(saved);
    }

    @Override
    public ExperienceModel findExperienceById(String id) {
        Experience experience = experienceRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Experience not found: " + id));
        return toModel(experience);
    }

    @Override
    public List<ExperienceModel> findAllByUserId(String userId) {
        return experienceRepository.findAllByUserId(userId).stream()
            .sorted(Comparator.comparing(Experience::getStartDate).reversed())
            .map(this::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public ExperienceModel updateExperience(UpdateExperienceModel updateExperienceModel) {
        Experience experience = experienceRepository.findById(updateExperienceModel.id())
            .orElseThrow(() -> new IllegalArgumentException(
                "Experience not found: " + updateExperienceModel.id()));

        if (updateExperienceModel.title() != null) {
            experience.setTitle(updateExperienceModel.title());
        }
        if (updateExperienceModel.company() != null) {
            experience.setCompany(updateExperienceModel.company());
        }
        if (updateExperienceModel.description() != null) {
            experience.setDescription(updateExperienceModel.description());
        }
        if (updateExperienceModel.startDate() != null) {
            experience.setStartDate(updateExperienceModel.startDate());
        }
        if (updateExperienceModel.endDate() != null) {
            experience.setEndDate(updateExperienceModel.endDate());
        }
        experience.setCurrent(updateExperienceModel.isCurrent());

        Experience saved = experienceRepository.save(experience);
        return toModel(saved);
    }

    @Override
    public void deleteExperience(String id) {
        experienceRepository.deleteById(id);
    }

    private ExperienceModel toModel(Experience experience) {
        return new ExperienceModel(
            experience.getId(),
            experience.getUserId(),
            experience.getTitle(),
            experience.getCompany(),
            experience.getDescription(),
            experience.getStartDate(),
            experience.getEndDate(),
            experience.isCurrent(),
            experience.getCreatedAt(),
            experience.getUpdatedAt()
        );
    }
}
