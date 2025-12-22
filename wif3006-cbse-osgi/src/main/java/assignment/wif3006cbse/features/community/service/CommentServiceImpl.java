package assignment.wif3006cbse.features.community.service;

import assignment.wif3006cbse.features.community.model.Comment;
import assignment.wif3006cbse.features.community.model.CommentModel;
import assignment.wif3006cbse.features.community.model.CreateCommentModel;
import assignment.wif3006cbse.features.community.model.UpdateCommentModel;
import assignment.wif3006cbse.features.community.repository.CommentRepository;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = CommentService.class)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Component(service = CommentService.class)
    public CommentServiceImpl() {
        // Default constructor for OSGi if needed, but field injection below is typical
        // or constructor injection with @Reference
        this.commentRepository = null; // Will be injected or we need constructor injection pattern
    }

    // Better OSGi pattern: Method injection or constructor injection (DS 1.4+).
    // Since we are using annotated fields/constructors, let's stick to constructor
    // injection if possible or field injection.

    @org.osgi.service.component.annotations.Activate
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
