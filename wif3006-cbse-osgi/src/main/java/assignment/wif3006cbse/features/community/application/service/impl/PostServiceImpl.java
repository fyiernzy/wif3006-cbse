package assignment.wif3006cbse.features.community.application.service.impl;

import assignment.wif3006cbse.features.community.application.dto.post.CreatePostModel;
import assignment.wif3006cbse.features.community.application.dto.post.PostModel;
import assignment.wif3006cbse.features.community.application.dto.post.UpdatePostModel;
import assignment.wif3006cbse.features.community.application.service.PostService;
import assignment.wif3006cbse.features.community.domain.entity.Post;
import assignment.wif3006cbse.features.community.domain.repository.PostRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component(service = PostService.class)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Activate
    public PostServiceImpl(@Reference PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostModel createPost(CreatePostModel createPostModel) {
        Post post = new Post(
            createPostModel.authorId(),
            createPostModel.title(),
            createPostModel.content()
        );
        Post saved = postRepository.save(post);

        return toModel(saved);
    }

    @Override
    public PostModel findPostById(String id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Post not found: " + id));
        return toModel(post);
    }

    @Override
    public List<PostModel> findPostsByAuthorId(String authorId) {
        return postRepository.findAllByAuthorId(authorId).stream()
            .map(this::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public PostModel updatePost(UpdatePostModel updatePostModel) {
        Post post = postRepository.findById(updatePostModel.id())
            .orElseThrow(
                () -> new IllegalArgumentException("Post not found: " + updatePostModel.id()));

        post.setTitle(updatePostModel.title());
        post.setContent(updatePostModel.content());
        post.setUpdatedAt(LocalDateTime.now());
        Post saved = postRepository.save(post);

        return toModel(saved);
    }

    @Override
    public void deletePostById(String id) {
        postRepository.deleteById(id);

    }

    private PostModel toModel(Post post) {
        return new PostModel(
            post.getId(),
            post.getAuthorId(),
            post.getTitle(),
            post.getContent(),
            post.getCreatedAt()
        );
    }
}
