package assignment.wif3006cbse.features.profile.domain.repository.impl;

import assignment.wif3006cbse.features.profile.domain.entity.Experience;
import assignment.wif3006cbse.features.profile.domain.repository.ExperienceRepository;
import assignment.wif3006cbse.shared.spi.FileBasedRepository;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = ExperienceRepository.class)
public class ExperienceRepositoryImpl extends FileBasedRepository<Experience, String> implements
    ExperienceRepository {

    public ExperienceRepositoryImpl() {
        super("experiences.dat", Experience::getId);
    }

    @Override
    public List<Experience> findAllByUserId(String userId) {
        return getStore().values().stream()
            .filter(e -> e.getUserId() != null && e.getUserId().equals(userId))
            .collect(Collectors.toList());
    }
}
