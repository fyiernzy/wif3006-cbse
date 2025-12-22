package assignment.wif3006cbse.features.community.repository.impl;

import assignment.wif3006cbse.features.community.model.Post;
import assignment.wif3006cbse.features.community.repository.PostRepository;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = PostRepository.class)
public class PostRepositoryImpl extends FileBasedRepository<Post, String> implements PostRepository {

    public PostRepositoryImpl() {
        super("posts.dat", Post::getId);
    }

    @Override
    public List<Post> findAllByAuthorId(String authorId) {
        return getStore().values().stream()
                .filter(p -> p.getAuthorId().equals(authorId))
                .collect(Collectors.toList());
    }
}
