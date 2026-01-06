package assignment.wif3006cbse.features.project.domain.repository;

import assignment.wif3006cbse.features.project.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
}