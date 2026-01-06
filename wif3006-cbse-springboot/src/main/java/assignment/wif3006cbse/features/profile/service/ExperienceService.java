package assignment.wif3006cbse.features.profile.service;

import assignment.wif3006cbse.features.profile.domain.entity.Experience;
import assignment.wif3006cbse.features.profile.domain.repository.ExperienceRepository;
import assignment.wif3006cbse.features.profile.dto.experience.CreateExperienceModel;
import assignment.wif3006cbse.features.profile.dto.experience.ExperienceModel;
import assignment.wif3006cbse.features.profile.dto.experience.UpdateExperienceModel;
import assignment.wif3006cbse.features.profile.mapper.ExperienceMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceMapper experienceMapper;
    private final ExperienceRepository experienceRepository;

    @Transactional
    public ExperienceModel createExperience(@Valid CreateExperienceModel createExperienceModel) {
        Experience experience = experienceMapper.toEntity(createExperienceModel);
        if (createExperienceModel.isCurrent() != null) {
            experience.setCurrent(createExperienceModel.isCurrent());
        }
        return experienceMapper.toModel(experienceRepository.save(experience));
    }

    @Transactional(readOnly = true)
    public ExperienceModel findExperienceById(@NotBlank String id) {
        Experience experience = experienceRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Experience not found."));
        return experienceMapper.toModel(experience);
    }

    @Transactional(readOnly = true)
    public List<ExperienceModel> findAllByUserId(@NotBlank String userId) {
        return experienceRepository.findAllByUserIdOrderByStartDateDesc(userId)
            .stream()
            .map(experienceMapper::toModel)
            .toList();
    }

    @Transactional(readOnly = true)
    public Page<ExperienceModel> findAllByUserId(@NotBlank String userId, Pageable pageable) {
        return experienceRepository.findAllByUserIdOrderByStartDateDesc(userId, pageable)
            .map(experienceMapper::toModel);
    }

    @Transactional
    public ExperienceModel updateExperience(@Valid UpdateExperienceModel updateExperienceModel) {
        Experience experience = experienceRepository.findById(updateExperienceModel.id())
            .orElseThrow(() -> new EntityNotFoundException("Experience not found."));
        experienceMapper.updateEntityFromUpdateModel(experience, updateExperienceModel);
        return experienceMapper.toModel(experienceRepository.save(experience));
    }

    @Transactional
    public void deleteExperienceById(@NotBlank String id) {
        experienceRepository.deleteById(id);
    }
}
