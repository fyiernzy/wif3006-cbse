package assignment.wif3006cbse.features.profile.domain.repository;

import assignment.wif3006cbse.features.profile.domain.entity.Experience;
import assignment.wif3006cbse.shared.spi.CrudRepository;

import java.util.List;

public interface ExperienceRepository extends CrudRepository<Experience, String> {

    List<Experience> findAllByUserId(String userId);
}
