package assignment.wif3006cbse.features.community.domain.repository;

import assignment.wif3006cbse.features.community.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

    Page<Post> findAllByAuthorIdOrderByCreatedAt(String authorId, Pageable pageable);
}
