package assignment.wif3006cbse.features.community.controller;

import assignment.wif3006cbse.features.community.dto.post.CreatePostModel;
import assignment.wif3006cbse.features.community.dto.post.PostModel;
import assignment.wif3006cbse.features.community.dto.post.UpdatePostModel;
import assignment.wif3006cbse.features.community.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public PostModel createPost(@RequestBody @Valid CreatePostModel createPostModel) {
        return postService.createPost(createPostModel);
    }

    @GetMapping("/{id}")
    public PostModel findPostById(@PathVariable @NotNull String id) {
        return postService.findPostById(id);
    }

        @GetMapping("/author/{authorId}")
    public PagedModel<PostModel> findPostsByAuthorId(@PathVariable @NotBlank String authorId,
                                                     @PageableDefault Pageable pageable) {
        return new PagedModel<>(
            postService.findAllByAuthorIdOrderByCreatedAtDesc(authorId, pageable));
    }

    @PutMapping
    public PostModel updatePost(@RequestBody @Valid UpdatePostModel updatePostModel) {
        return postService.updatePost(updatePostModel);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable @NotNull String id) {
        postService.deletePostById(id);
    }
}
