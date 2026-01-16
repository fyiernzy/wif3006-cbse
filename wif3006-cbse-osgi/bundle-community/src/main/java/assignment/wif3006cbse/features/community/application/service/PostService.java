package assignment.wif3006cbse.features.community.application.service;

import assignment.wif3006cbse.features.community.application.dto.post.CreatePostModel;
import assignment.wif3006cbse.features.community.application.dto.post.PostModel;
import assignment.wif3006cbse.features.community.application.dto.post.UpdatePostModel;
import assignment.wif3006cbse.shared.pagination.Page;
import assignment.wif3006cbse.shared.pagination.Pageable;

public interface PostService {

    PostModel createPost(CreatePostModel createPostModel);

    PostModel findPostById(String id);

    Page<PostModel> findPostsByAuthorId(String authorId, Pageable pageable);

    PostModel updatePost(UpdatePostModel updatePostModel);

    void deletePostById(String id);
}
