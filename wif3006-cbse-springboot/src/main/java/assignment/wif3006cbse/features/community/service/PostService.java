package assignment.wif3006cbse.features.community.service;

import assignment.wif3006cbse.features.community.domain.entity.Post;
import assignment.wif3006cbse.features.community.domain.repository.PostRepository;
import assignment.wif3006cbse.features.community.dto.post.CreatePostModel;
import assignment.wif3006cbse.features.community.dto.post.PostModel;
import assignment.wif3006cbse.features.community.dto.post.UpdatePostModel;
import assignment.wif3006cbse.features.community.mapper.PostMapper;
import assignment.wif3006cbse.features.user.domain.UserRepository;
import assignment.wif3006cbse.utils.Asserts;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostModel createPost(@Valid CreatePostModel createPostModel) {
        String authorId = createPostModel.authorId();
        Asserts.state(userRepository.existsById(authorId), "The user does not exist");
        Post post = postMapper.toEntity(createPostModel);
        return postMapper.toModel(postRepository.save(post));
    }

    @Transactional(readOnly = true)
    public PostModel findPostById(@NotNull String id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Post not found."));
        return postMapper.toModel(post);
    }

    @Transactional(readOnly = true)
    public Page<PostModel> findAllByAuthorIdOrderByCreatedAtDesc(String authorId,
                                                                 Pageable pageable) {
        return postRepository.findAllByAuthorIdOrderByCreatedAt(authorId, pageable)
            .map(postMapper::toModel);
    }

    @Transactional
    public PostModel updatePost(@Valid UpdatePostModel updatePostModel) {
        Post post = postRepository.findById(updatePostModel.id())
            .orElseThrow(() -> new EntityNotFoundException("Post not found."));
        postMapper.updateEntityFromUpdateModel(post, updatePostModel);
        return postMapper.toModel(postRepository.save(post));
    }

    @Transactional
    public void deletePostById(@NotNull String id) {
        postRepository.deleteById(id);
    }
}
