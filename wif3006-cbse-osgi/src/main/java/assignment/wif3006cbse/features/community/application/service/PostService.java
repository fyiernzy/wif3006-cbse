package assignment.wif3006cbse.features.community.application.service;

import assignment.wif3006cbse.features.community.application.dto.CreatePostModel;
import assignment.wif3006cbse.features.community.application.dto.PostModel;
import assignment.wif3006cbse.features.community.application.dto.UpdatePostModel;
import java.util.List;

public interface PostService {
    PostModel createPost(CreatePostModel createPostModel);

    PostModel findPostById(String id);

    List<PostModel> findPostsByAuthorId(String authorId);

    PostModel updatePost(UpdatePostModel updatePostModel);

    void deletePostById(String id);
}
