package assignment.wif3006cbse.features.community.domain.repository.impl;

import assignment.wif3006cbse.features.community.domain.entity.ThreadEntity;
import assignment.wif3006cbse.features.community.domain.repository.ThreadEntityRepository;
import assignment.wif3006cbse.shared.spi.FileBasedRepository;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = ThreadEntityRepository.class)
public class ThreadEntityRepositoryImpl extends FileBasedRepository<ThreadEntity, String> implements
    ThreadEntityRepository {

    public ThreadEntityRepositoryImpl() {
        super("threads.dat", ThreadEntity::getId);
    }

    @Override
    public List<ThreadEntity> findAllByAuthorId(String authorId) {
        return getStore().values().stream()
            .filter(t -> t.getAuthorId().equals(authorId))
            .collect(Collectors.toList());
    }

    @Override
    public List<ThreadEntity> findAllByPostId(String postId) {
        return getStore().values().stream()
            .filter(t -> postId.equals(t.getPostId()))
            .collect(Collectors.toList());
    }
}
