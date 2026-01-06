package assignment.wif3006cbse.features.project.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import assignment.wif3006cbse.features.project.domain.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
}