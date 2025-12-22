package assignment.wif3006cbse.features.community.repository;

import assignment.wif3006cbse.features.community.model.Comment;
import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, String> {
    List<Comment> findAllByThreadId(String threadId);
}
