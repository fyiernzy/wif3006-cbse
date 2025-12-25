package assignment.wif3006cbse.features.community.application.service;

import assignment.wif3006cbse.features.community.application.dto.reaction.CreateReactionModel;
import assignment.wif3006cbse.features.community.application.dto.reaction.ReactionModel;
import assignment.wif3006cbse.features.community.application.dto.reaction.UpdateReactionModel;

import java.util.List;

public interface ReactionService {

    ReactionModel createReaction(CreateReactionModel createReactionModel);

    List<ReactionModel> findReactionsByPostId(String postId);

    List<ReactionModel> findReactionsByThreadId(String threadId);

    List<ReactionModel> findReactionsByCommentId(String commentId);

    ReactionModel updateReaction(UpdateReactionModel updateReactionModel);

    void deleteReactionById(String id);
}
