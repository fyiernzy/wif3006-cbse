package assignment.wif3006cbse.features.community.domain.repository;

import assignment.wif3006cbse.features.community.domain.entity.ThreadEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadEntityRepository extends JpaRepository<ThreadEntity, String> {

    Page<ThreadEntity> findAllByPostId(String postId, Pageable pageable);
}
