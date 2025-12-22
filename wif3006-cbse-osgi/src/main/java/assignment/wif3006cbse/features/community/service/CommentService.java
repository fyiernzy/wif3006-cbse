package assignment.wif3006cbse.features.community.service;

import assignment.wif3006cbse.features.community.model.CommentModel;
import assignment.wif3006cbse.features.community.model.CreateCommentModel;
import assignment.wif3006cbse.features.community.model.UpdateCommentModel;
import java.util.List;

public interface CommentService {
    CommentModel createComment(CreateCommentModel createCommentModel);

    List<CommentModel> findCommentsByThreadId(String threadId);

    CommentModel updateComment(UpdateCommentModel updateCommentModel);

    void deleteCommentById(String id);
}
