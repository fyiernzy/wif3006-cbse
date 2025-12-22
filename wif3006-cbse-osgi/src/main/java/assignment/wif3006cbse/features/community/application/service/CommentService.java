package assignment.wif3006cbse.features.community.application.service;

import assignment.wif3006cbse.features.community.application.dto.CommentModel;
import assignment.wif3006cbse.features.community.application.dto.CreateCommentModel;
import assignment.wif3006cbse.features.community.application.dto.UpdateCommentModel;
import java.util.List;

public interface CommentService {
    CommentModel createComment(CreateCommentModel createCommentModel);

    List<CommentModel> findCommentsByThreadId(String threadId);

    CommentModel updateComment(UpdateCommentModel updateCommentModel);

    void deleteCommentById(String id);
}
