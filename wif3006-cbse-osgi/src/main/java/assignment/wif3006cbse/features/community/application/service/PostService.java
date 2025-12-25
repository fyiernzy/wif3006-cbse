package assignment.wif3006cbse.features.community.application.service;

import assignment.wif3006cbse.features.community.application.dto.post.CreatePostModel;
import assignment.wif3006cbse.features.community.application.dto.post.PostModel;
import assignment.wif3006cbse.features.community.application.dto.post.UpdatePostModel;

import java.util.List;

public interface PostService {

    PostModel createPost(CreatePostModel createPostModel);

    PostModel findPostById(String id);

    List<PostModel> findAllByAuthorIdOrderByCreatedAtDesc(String authorId);

    PostModel updatePost(UpdatePostModel updatePostModel);

    void deletePostById(String id);
}
