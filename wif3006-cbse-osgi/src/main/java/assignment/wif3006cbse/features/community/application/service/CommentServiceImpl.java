package assignment.wif3006cbse.features.community.application.service;

import assignment.wif3006cbse.features.community.domain.entity.Comment;
import assignment.wif3006cbse.features.community.application.dto.CommentModel;
import assignment.wif3006cbse.features.community.application.dto.CreateCommentModel;
import assignment.wif3006cbse.features.community.application.dto.UpdateCommentModel;
import assignment.wif3006cbse.features.community.domain.repository.CommentRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = CommentService.class)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Activate
    public CommentServiceImpl(@Reference CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentModel createComment(CreateCommentModel createCommentModel) {
        Comment comment = new Comment(
                createCommentModel.threadId(),
                createCommentModel.authorId(),
                createCommentModel.content());
        Comment saved = commentRepository.save(comment);
        System.out.println("Created comment: " + saved.getId());
        return toModel(saved);
    }

    @Override
    public List<CommentModel> findCommentsByThreadId(String threadId) {
        return commentRepository.findAllByThreadId(threadId).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public CommentModel updateComment(UpdateCommentModel updateCommentModel) {
        Comment comment = commentRepository.findById(updateCommentModel.id())
                .orElseThrow(() -> new IllegalArgumentException("Comment not found: " + updateCommentModel.id()));

        comment.setContent(updateCommentModel.content());
        Comment saved = commentRepository.save(comment);
        System.out.println("Updated comment: " + saved.getId());
        return toModel(saved);
    }

    @Override
    public void deleteCommentById(String id) {
        commentRepository.deleteById(id);
        System.out.println("Deleted comment: " + id);
    }

    private CommentModel toModel(Comment comment) {
        return new CommentModel(
                comment.getId(),
                comment.getThreadId(),
                comment.getAuthorId(),
                comment.getContent());
    }
}
