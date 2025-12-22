package assignment.wif3006cbse.features.community.repository;

import assignment.wif3006cbse.features.community.model.Post;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, String> {
    List<Post> findAllByAuthorId(String authorId);
}
