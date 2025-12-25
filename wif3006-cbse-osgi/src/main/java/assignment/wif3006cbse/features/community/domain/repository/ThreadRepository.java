package assignment.wif3006cbse.features.community.domain.repository;

import assignment.wif3006cbse.features.community.domain.entity.Thread;
import assignment.wif3006cbse.shared.spi.CrudRepository;

import java.util.List;

public interface ThreadRepository extends CrudRepository<Thread, String> {

    List<Thread> findAllByAuthorId(String authorId);

    List<Thread> findAllByPostId(String postId);
}
