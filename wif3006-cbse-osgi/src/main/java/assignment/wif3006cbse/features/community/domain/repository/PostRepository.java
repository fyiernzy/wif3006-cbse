package assignment.wif3006cbse.features.community.domain.repository;

import assignment.wif3006cbse.features.community.domain.entity.Post;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, String> {
    List<Post> findAllByAuthorId(String authorId);
}
