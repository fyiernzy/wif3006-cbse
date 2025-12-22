package assignment.wif3006cbse.features.community.service;

import assignment.wif3006cbse.features.community.model.CreatePostModel;
import assignment.wif3006cbse.features.community.model.Post;
import assignment.wif3006cbse.features.community.model.PostModel;
import assignment.wif3006cbse.features.community.model.UpdatePostModel;
import assignment.wif3006cbse.features.community.repository.PostRepository;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = PostService.class)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @org.osgi.service.component.annotations.Activate
    public PostServiceImpl(@Reference PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Fallback no-args if DS requires it momentarily before binding (rare in modern
    // DS but safe)
    public PostServiceImpl() {
        this.postRepository = null;
    }

    @Override
    public PostModel createPost(CreatePostModel createPostModel) {
        Post post = new Post(
                createPostModel.authorId(),
                createPostModel.title(),
                createPostModel.content());
        Post saved = postRepository.save(post);
        System.out.println("Created post: " + saved.getId());
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
                .orElseThrow(() -> new IllegalArgumentException("Post not found: " + updatePostModel.id()));

        post.setTitle(updatePostModel.title());
        post.setContent(updatePostModel.content());
        Post saved = postRepository.save(post);
        System.out.println("Updated post: " + saved.getId());
        return toModel(saved);
    }

    @Override
    public void deletePostById(String id) {
        postRepository.deleteById(id);
        System.out.println("Deleted post: " + id);
    }

    private PostModel toModel(Post post) {
        return new PostModel(
                post.getId(),
                post.getAuthorId(),
                post.getTitle(),
                post.getContent());
    }
}
