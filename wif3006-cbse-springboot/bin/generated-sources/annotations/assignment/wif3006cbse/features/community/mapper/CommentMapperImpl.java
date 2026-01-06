package assignment.wif3006cbse.features.community.mapper;

import assignment.wif3006cbse.features.community.domain.entity.Comment;
import assignment.wif3006cbse.features.community.dto.comment.CommentModel;
import assignment.wif3006cbse.features.community.dto.comment.CreateCommentModel;
import assignment.wif3006cbse.features.community.dto.comment.UpdateCommentModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-03T17:31:40+0800",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentModel toModel(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        String id = null;
        String threadId = null;
        String authorId = null;
        String content = null;

        if ( comment.getId() != null ) {
            id = comment.getId();
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

        if ( createCommentModel.authorId() != null ) {
            comment.setAuthorId( createCommentModel.authorId() );
        }
        if ( createCommentModel.content() != null ) {
            comment.setContent( createCommentModel.content() );
        }
        if ( createCommentModel.threadId() != null ) {
            comment.setThreadId( createCommentModel.threadId() );
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
