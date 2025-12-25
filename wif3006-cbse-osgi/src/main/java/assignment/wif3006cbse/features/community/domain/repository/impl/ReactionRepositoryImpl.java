package assignment.wif3006cbse.features.community.domain.repository.impl;

import assignment.wif3006cbse.features.community.domain.entity.Reaction;
import assignment.wif3006cbse.features.community.domain.repository.ReactionRepository;
import assignment.wif3006cbse.features.community.domain.vo.ReactionSourceTypeEnum;
import assignment.wif3006cbse.shared.spi.FileBasedRepository;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = ReactionRepository.class)
public class ReactionRepositoryImpl extends FileBasedRepository<Reaction, String> implements
    ReactionRepository {

    public ReactionRepositoryImpl() {
        super("reactions.dat", Reaction::getId);
    }

    @Override
    public List<Reaction> findAllBySourceTypeAndSourceId(ReactionSourceTypeEnum sourceType,
                                                         String sourceId) {
        return getStore().values().stream()
            .filter(r -> r.getSourceType() == sourceType && r.getSourceId().equals(sourceId))
            .collect(Collectors.toList());
    }
}
