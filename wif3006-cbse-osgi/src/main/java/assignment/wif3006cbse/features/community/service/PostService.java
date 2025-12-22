package assignment.wif3006cbse.features.community.service;

import assignment.wif3006cbse.features.community.model.CreatePostModel;
import assignment.wif3006cbse.features.community.model.PostModel;
import assignment.wif3006cbse.features.community.model.UpdatePostModel;
import java.util.List;

public interface PostService {
    PostModel createPost(CreatePostModel createPostModel);

    PostModel findPostById(String id);

    List<PostModel> findPostsByAuthorId(String authorId);

    PostModel updatePost(UpdatePostModel updatePostModel);

    void deletePostById(String id);
}
