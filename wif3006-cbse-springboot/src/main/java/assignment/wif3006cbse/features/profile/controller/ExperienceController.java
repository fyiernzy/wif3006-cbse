package assignment.wif3006cbse.features.profile.controller;

import assignment.wif3006cbse.features.profile.dto.experience.CreateExperienceModel;
import assignment.wif3006cbse.features.profile.dto.experience.ExperienceModel;
import assignment.wif3006cbse.features.profile.dto.experience.UpdateExperienceModel;
import assignment.wif3006cbse.features.profile.service.ExperienceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping
    public ExperienceModel createExperience(@RequestBody @Valid CreateExperienceModel createExperienceModel) {
        return experienceService.createExperience(createExperienceModel);
    }

    @GetMapping("/{id}")
    public ExperienceModel findExperienceById(@PathVariable @NotBlank String id) {
        return experienceService.findExperienceById(id);
    }

    @GetMapping("/user/{userId}")
    public List<ExperienceModel> findAllByUserId(@PathVariable @NotBlank String userId) {
        return experienceService.findAllByUserId(userId);
    }

    @GetMapping("/user/{userId}/paged")
    public PagedModel<ExperienceModel> findAllByUserIdPaged(@PathVariable @NotBlank String userId,
                                                            @PageableDefault Pageable pageable) {
        return new PagedModel<>(experienceService.findAllByUserId(userId, pageable));
    }

    @PutMapping
    public ExperienceModel updateExperience(@RequestBody @Valid UpdateExperienceModel updateExperienceModel) {
        return experienceService.updateExperience(updateExperienceModel);
    }

    @DeleteMapping("/{id}")
    public void deleteExperience(@PathVariable @NotBlank String id) {
        experienceService.deleteExperienceById(id);
    }
}
