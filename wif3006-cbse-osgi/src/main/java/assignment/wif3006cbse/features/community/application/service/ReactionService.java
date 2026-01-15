package assignment.wif3006cbse.features.community.application.service;

import assignment.wif3006cbse.features.community.application.dto.reaction.CreateReactionModel;
import assignment.wif3006cbse.features.community.application.dto.reaction.ReactionModel;
import assignment.wif3006cbse.features.community.application.dto.reaction.UpdateReactionModel;
import assignment.wif3006cbse.shared.pagination.Page;
import assignment.wif3006cbse.shared.pagination.Pageable;

public interface ReactionService {

    ReactionModel createReaction(CreateReactionModel createReactionModel);

    Page<ReactionModel> findReactionsByPostId(String postId, Pageable pageable);

    Page<ReactionModel> findReactionsByThreadId(String threadId, Pageable pageable);

    Page<ReactionModel> findReactionsByCommentId(String commentId, Pageable pageable);

    ReactionModel updateReaction(UpdateReactionModel updateReactionModel);

    void deleteReactionById(String id);
}
