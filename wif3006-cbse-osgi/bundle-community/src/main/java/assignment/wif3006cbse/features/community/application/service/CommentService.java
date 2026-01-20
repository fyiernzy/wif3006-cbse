package assignment.wif3006cbse.features.community.application.service;

import assignment.wif3006cbse.features.community.application.dto.comment.CommentModel;
import assignment.wif3006cbse.features.community.application.dto.comment.CreateCommentModel;
import assignment.wif3006cbse.features.community.application.dto.comment.UpdateCommentModel;
import assignment.wif3006cbse.shared.pagination.Page;
import assignment.wif3006cbse.shared.pagination.Pageable;

public interface CommentService {
    CommentModel createComment(CreateCommentModel createCommentModel);

    Page<CommentModel> findCommentsByThreadId(String threadId, Pageable pageable);

    CommentModel updateComment(UpdateCommentModel updateCommentModel);

    void deleteCommentById(String id);
}
