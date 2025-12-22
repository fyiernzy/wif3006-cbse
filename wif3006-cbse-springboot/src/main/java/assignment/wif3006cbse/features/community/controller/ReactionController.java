package assignment.wif3006cbse.features.community.controller;

import assignment.wif3006cbse.features.community.dto.reaction.CreateReactionModel;
import assignment.wif3006cbse.features.community.dto.reaction.ReactionModel;
import assignment.wif3006cbse.features.community.dto.reaction.UpdateReactionModel;
import assignment.wif3006cbse.features.community.service.ReactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reactions")
public class ReactionController {

    private final ReactionService reactionService;

    @PostMapping
    public ReactionModel createReaction(@RequestBody @Valid CreateReactionModel createReactionModel) {
        return reactionService.createReaction(createReactionModel);
    }

    @GetMapping("/post/{postId}")
    public PagedModel<ReactionModel> findReactionsByPostId(@PathVariable @NotBlank String postId,
                                                           @PageableDefault Pageable pageable) {
        return new PagedModel<>(reactionService.findReactionsByPostId(postId, pageable));
    }

    @GetMapping("/thread/{threadId}")
    public PagedModel<ReactionModel> findReactionsByThreadId(@PathVariable @NotBlank String threadId,
                                                             @PageableDefault Pageable pageable) {
        return new PagedModel<>(reactionService.findReactionsByThreadId(threadId, pageable));
    }

    @GetMapping("/comment/{commentId}")
    public PagedModel<ReactionModel> findReactionsByCommentId(@PathVariable @NotBlank String commentId,
                                                              @PageableDefault Pageable pageable) {
        return new PagedModel<>(reactionService.findReactionsByCommentId(commentId, pageable));
    }

    @PutMapping
    public ReactionModel updateReaction(@RequestBody @Valid UpdateReactionModel updateReactionModel) {
        return reactionService.updateReaction(updateReactionModel);
    }

    @DeleteMapping("/{id}")
    public void deleteReaction(@PathVariable @NotNull String id) {
        reactionService.deleteReactionById(id);
    }
}
