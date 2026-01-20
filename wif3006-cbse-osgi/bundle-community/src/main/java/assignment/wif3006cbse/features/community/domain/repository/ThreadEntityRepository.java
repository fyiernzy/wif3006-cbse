package assignment.wif3006cbse.features.community.domain.repository;

import assignment.wif3006cbse.features.community.domain.entity.ThreadEntity;
import assignment.wif3006cbse.shared.spi.CrudRepository;

import java.util.List;

public interface ThreadEntityRepository extends CrudRepository<ThreadEntity, String> {

    List<ThreadEntity> findAllByPostId(String postId);
}
