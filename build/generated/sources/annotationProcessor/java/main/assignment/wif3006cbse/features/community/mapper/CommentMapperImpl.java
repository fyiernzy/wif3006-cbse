package assignment.wif3006cbse.features.community.mapper;

import assignment.wif3006cbse.features.community.domain.entity.Comment;
import assignment.wif3006cbse.features.community.dto.comment.CommentModel;
import assignment.wif3006cbse.features.community.dto.comment.CreateCommentModel;
import assignment.wif3006cbse.features.community.dto.comment.UpdateCommentModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-16T12:30:24+0800",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentModel toModel(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        Long id = null;
        String threadId = null;
        String authorId = null;
        String content = null;

        if ( comment.getId() != null ) {
            id = Long.parseLong( comment.getId() );
        }
        if ( comment.getThreadId() != null ) {
            threadId = comment.getThreadId();
        }
        if ( comment.getAuthorId() != null ) {
            authorId = comment.getAuthorId();
        }
        if ( comment.getContent() != null ) {
            content = comment.getContent();
        }

        CommentModel commentModel = new CommentModel( id, threadId, authorId, content );

        return commentModel;
    }

    @Override
    public Comment toEntity(CreateCommentModel createCommentModel) {
        if ( createCommentModel == null ) {
            return null;
        }

        Comment comment = new Comment();

        if ( createCommentModel.threadId() != null ) {
            comment.setThreadId( createCommentModel.threadId() );
        }
        if ( createCommentModel.authorId() != null ) {
            comment.setAuthorId( createCommentModel.authorId() );
        }
        if ( createCommentModel.content() != null ) {
            comment.setContent( createCommentModel.content() );
        }

        return comment;
    }

    @Override
    public void updateEntityFromUpdateModel(Comment comment, UpdateCommentModel updateCommentModel) {
        if ( updateCommentModel == null ) {
            return;
        }

        if ( updateCommentModel.content() != null ) {
            comment.setContent( updateCommentModel.content() );
        }
    }
}
