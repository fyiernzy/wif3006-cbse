package assignment.wif3006cbse.features.profile.domain.repository;

import assignment.wif3006cbse.features.profile.domain.entity.Experience;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, String> {

    List<Experience> findAllByUserIdOrderByStartDateDesc(String userId);

    Page<Experience> findAllByUserIdOrderByStartDateDesc(String userId, Pageable pageable);
}
