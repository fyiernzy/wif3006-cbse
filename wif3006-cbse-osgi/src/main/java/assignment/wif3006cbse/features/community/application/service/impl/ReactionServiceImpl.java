package assignment.wif3006cbse.features.community.application.service.impl;

import assignment.wif3006cbse.features.community.application.dto.reaction.CreateReactionModel;
import assignment.wif3006cbse.features.community.application.dto.reaction.ReactionModel;
import assignment.wif3006cbse.features.community.application.dto.reaction.UpdateReactionModel;
import assignment.wif3006cbse.features.community.application.service.ReactionService;
import assignment.wif3006cbse.features.community.domain.entity.Reaction;
import assignment.wif3006cbse.features.community.domain.repository.ReactionRepository;
import assignment.wif3006cbse.features.community.domain.vo.ReactionSourceTypeEnum;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = ReactionService.class)
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepository reactionRepository;

    @Activate
    public ReactionServiceImpl(@Reference ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }

    @Override
    public ReactionModel createReaction(CreateReactionModel createReactionModel) {
        Reaction reaction = new Reaction(
                createReactionModel.sourceType(),
                createReactionModel.sourceId(),
                createReactionModel.authorId(),
                createReactionModel.reaction());
        Reaction saved = reactionRepository.save(reaction);
        return toModel(saved);
    }

    @Override
    public List<ReactionModel> findReactionsByPostId(String postId) {
        return reactionRepository.findAllBySourceTypeAndSourceId(ReactionSourceTypeEnum.POST, postId).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReactionModel> findReactionsByThreadId(String threadId) {
        return reactionRepository.findAllBySourceTypeAndSourceId(ReactionSourceTypeEnum.THREAD, threadId).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReactionModel> findReactionsByCommentId(String commentId) {
        return reactionRepository.findAllBySourceTypeAndSourceId(ReactionSourceTypeEnum.COMMENT, commentId).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public ReactionModel updateReaction(UpdateReactionModel updateReactionModel) {
        Reaction reaction = reactionRepository.findById(updateReactionModel.id())
                .orElseThrow(() -> new IllegalArgumentException("Reaction not found: " + updateReactionModel.id()));

        reaction.setReaction(updateReactionModel.reaction());
        Reaction saved = reactionRepository.save(reaction);
        return toModel(saved);
    }

    @Override
    public void deleteReactionById(String id) {
        reactionRepository.deleteById(id);
    }

    private ReactionModel toModel(Reaction reaction) {
        return new ReactionModel(
                reaction.getId(),
                reaction.getSourceType(),
                reaction.getSourceId(),
                reaction.getAuthorId(),
                reaction.getReaction());
    }
}
