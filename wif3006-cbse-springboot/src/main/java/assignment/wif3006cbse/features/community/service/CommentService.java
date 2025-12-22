package assignment.wif3006cbse.features.community.service;

import assignment.wif3006cbse.features.community.domain.entity.Comment;
import assignment.wif3006cbse.features.community.domain.repository.CommentRepository;
import assignment.wif3006cbse.features.community.domain.repository.ThreadEntityRepository;
import assignment.wif3006cbse.features.community.dto.comment.CommentModel;
import assignment.wif3006cbse.features.community.dto.comment.CreateCommentModel;
import assignment.wif3006cbse.features.community.dto.comment.UpdateCommentModel;
import assignment.wif3006cbse.features.community.mapper.CommentMapper;
import assignment.wif3006cbse.features.user.domain.UserRepository;
import assignment.wif3006cbse.utils.Asserts;
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
public class CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final ThreadEntityRepository threadEntityRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentModel createComment(@Valid CreateCommentModel createCommentModel) {
        String threadId = createCommentModel.threadId();
        Asserts.state(threadEntityRepository.existsById(threadId),
            "Thread (id=%s) doesn't exist!".formatted(threadId));

        String authorId = createCommentModel.authorId();
        Asserts.state(userRepository.existsById(authorId),
            "Author (id=%s) doesn't exist!".formatted(authorId));

        Comment comment = commentMapper.toEntity(createCommentModel);
        return commentMapper.toModel(commentRepository.save(comment));
    }

    @Transactional(readOnly = true)
    public Page<CommentModel> findCommentsByThreadId(@NotBlank String threadId,
                                                     @NotNull Pageable pageable) {
        return commentRepository.findAllByThreadId(threadId, pageable)
            .map(commentMapper::toModel);
    }

    @Transactional
    public CommentModel updateComment(@Valid UpdateCommentModel updateCommentModel) {
        Comment comment = commentRepository.findById(updateCommentModel.id())
            .orElseThrow(() -> new EntityNotFoundException("Comment not found."));
        commentMapper.updateEntityFromUpdateModel(comment, updateCommentModel);
        return commentMapper.toModel(commentRepository.save(comment));
    }

    @Transactional
    public void deleteCommentById(@NotBlank String id) {
        commentRepository.deleteById(id);
    }
}
