package assignment.wif3006cbse.features.community.controller;

import assignment.wif3006cbse.features.community.dto.comment.CommentModel;
import assignment.wif3006cbse.features.community.dto.comment.CreateCommentModel;
import assignment.wif3006cbse.features.community.dto.comment.UpdateCommentModel;
import assignment.wif3006cbse.features.community.service.CommentService;
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
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public CommentModel createComment(@RequestBody @Valid CreateCommentModel createCommentModel) {
        return commentService.createComment(createCommentModel);
    }

    @GetMapping("/{threadId}")
    public PagedModel<CommentModel> findCommentByThreadId(@PathVariable @NotNull String threadId,
                                                          @PageableDefault Pageable pageable) {
        return new PagedModel<>(commentService.findCommentsByThreadId(threadId, pageable));
    }

    @PutMapping
    public CommentModel updateComment(@RequestBody @Valid UpdateCommentModel updateCommentModel) {
        return commentService.updateComment(updateCommentModel);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable @NotBlank String id) {
        commentService.deleteCommentById(id);
    }
}
