package assignment.wif3006cbse.features.community.service;

import assignment.wif3006cbse.features.community.domain.entity.Reaction;
import assignment.wif3006cbse.features.community.domain.repository.ReactionRepository;
import assignment.wif3006cbse.features.community.domain.vo.ReactionSourceTypeEnum;
import assignment.wif3006cbse.features.community.dto.reaction.CreateReactionModel;
import assignment.wif3006cbse.features.community.dto.reaction.ReactionModel;
import assignment.wif3006cbse.features.community.dto.reaction.UpdateReactionModel;
import assignment.wif3006cbse.features.community.mapper.ReactionMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class ReactionService {

    private final ReactionMapper reactionMapper;
    private final ReactionRepository reactionRepository;

    @Transactional
    public ReactionModel createReaction(@Valid CreateReactionModel createReactionModel) {
        Reaction reaction = reactionMapper.toEntity(createReactionModel);
        return reactionMapper.toModel(reactionRepository.save(reaction));
    }

    @Transactional(readOnly = true)
    public Page<ReactionModel> findReactionsByPostId(@NotBlank String postId,
                                                     @NotNull Pageable pageable) {
        return reactionRepository
            .findAllBySourceTypeAndSourceId(ReactionSourceTypeEnum.POST, postId, pageable)
            .map(reactionMapper::toModel);
    }

    @Transactional(readOnly = true)
    public Page<ReactionModel> findReactionsByThreadId(@NotBlank String threadId,
                                                       @NotNull Pageable pageable) {
        return reactionRepository
            .findAllBySourceTypeAndSourceId(ReactionSourceTypeEnum.THREAD, threadId, pageable)
            .map(reactionMapper::toModel);
    }

    @Transactional(readOnly = true)
    public Page<ReactionModel> findReactionsByCommentId(@NotBlank String commentId,
                                                        @NotNull Pageable pageable) {
        return reactionRepository
            .findAllBySourceTypeAndSourceId(ReactionSourceTypeEnum.COMMENT, commentId, pageable)
            .map(reactionMapper::toModel);
    }

    @Transactional
    public ReactionModel updateReaction(@Valid UpdateReactionModel updateReactionModel) {
        Reaction reaction = reactionRepository.findById(updateReactionModel.id())
            .orElseThrow(() -> new EntityNotFoundException("Reaction not found."));
        reactionMapper.updateEntityFromUpdateModel(reaction, updateReactionModel);
        return reactionMapper.toModel(reactionRepository.save(reaction));
    }

    @Transactional
    public void deleteReactionById(@NotNull String id) {
        reactionRepository.deleteById(id);
    }
}
