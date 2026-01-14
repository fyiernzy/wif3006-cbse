package assignment.wif3006cbse.features.community.domain.repository.impl;

import assignment.wif3006cbse.features.community.domain.entity.Post;
import assignment.wif3006cbse.features.community.domain.repository.PostRepository;
import assignment.wif3006cbse.shared.spi.FileBasedRepository;
import org.osgi.service.component.annotations.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component(service = PostRepository.class)
public class PostRepositoryImpl extends FileBasedRepository<Post, String> implements
    PostRepository {

    public PostRepositoryImpl() {
        super("posts.dat", Post::getId);
    }

    @Override
    public List<Post> findAllByAuthorId(String authorId) {
        return getStore().values().stream()
            .filter(post -> post.getAuthorId().equals(authorId))
            .sorted(Comparator.comparing(Post::getCreatedAt))
            .collect(Collectors.toList());
    }
}
