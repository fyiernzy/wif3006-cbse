package assignment.wif3006cbse.features.community.domain.repository;

import assignment.wif3006cbse.features.community.domain.entity.Reaction;
import assignment.wif3006cbse.features.community.domain.vo.ReactionSourceTypeEnum;
import assignment.wif3006cbse.shared.spi.CrudRepository;

import java.util.List;

public interface ReactionRepository extends CrudRepository<Reaction, String> {

    List<Reaction> findAllBySourceTypeAndSourceId(ReactionSourceTypeEnum sourceType,
                                                  String sourceId);
}
