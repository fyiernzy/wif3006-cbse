package assignment.wif3006cbse.features.community.domain.repository.impl;

import assignment.wif3006cbse.features.community.domain.entity.Comment;
import assignment.wif3006cbse.features.community.domain.repository.CommentRepository;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = CommentRepository.class)
public class CommentRepositoryImpl extends FileBasedRepository<Comment, String> implements CommentRepository {

    public CommentRepositoryImpl() {
        super("comments.dat", Comment::getId);
    }

    @Override
    public List<Comment> findAllByThreadId(String threadId) {
        return getStore().values().stream()
                .filter(c -> c.getThreadId().equals(threadId))
                .collect(Collectors.toList());
    }
}
