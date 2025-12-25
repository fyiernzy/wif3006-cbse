package assignment.wif3006cbse.features.community.domain.repository.impl;

import assignment.wif3006cbse.features.community.domain.entity.Thread;
import assignment.wif3006cbse.features.community.domain.repository.ThreadRepository;
import assignment.wif3006cbse.shared.spi.FileBasedRepository;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = ThreadRepository.class)
public class ThreadRepositoryImpl extends FileBasedRepository<Thread, String> implements
    ThreadRepository {

    public ThreadRepositoryImpl() {
        super("threads.dat", Thread::getId);
    }

    @Override
    public List<Thread> findAllByAuthorId(String authorId) {
        return getStore().values().stream()
            .filter(t -> t.getAuthorId().equals(authorId))
            .collect(Collectors.toList());
    }

    @Override
    public List<Thread> findAllByPostId(String postId) {
        return getStore().values().stream()
            .filter(t -> postId.equals(t.getPostId()))
            .collect(Collectors.toList());
    }
}
