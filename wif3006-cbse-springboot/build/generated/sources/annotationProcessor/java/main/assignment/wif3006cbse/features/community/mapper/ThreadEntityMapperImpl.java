package assignment.wif3006cbse.features.community.mapper;

import assignment.wif3006cbse.features.community.domain.entity.ThreadEntity;
import assignment.wif3006cbse.features.community.dto.thread.CreateThreadEntityModel;
import assignment.wif3006cbse.features.community.dto.thread.ThreadEntityModel;
import assignment.wif3006cbse.features.community.dto.thread.UpdateThreadEntityModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-16T12:30:25+0800",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class ThreadEntityMapperImpl implements ThreadEntityMapper {

    @Override
    public ThreadEntityModel toModel(ThreadEntity threadEntity) {
        if ( threadEntity == null ) {
            return null;
        }

        String id = null;
        String postId = null;
        String authorId = null;
        String content = null;

        if ( threadEntity.getId() != null ) {
            id = threadEntity.getId();
        }
        if ( threadEntity.getPostId() != null ) {
            postId = threadEntity.getPostId();
        }
        if ( threadEntity.getAuthorId() != null ) {
            authorId = threadEntity.getAuthorId();
        }
        if ( threadEntity.getContent() != null ) {
            content = threadEntity.getContent();
        }

        ThreadEntityModel threadEntityModel = new ThreadEntityModel( id, postId, authorId, content );

        return threadEntityModel;
    }

    @Override
    public ThreadEntity toEntity(CreateThreadEntityModel createThreadEntityModel) {
        if ( createThreadEntityModel == null ) {
            return null;
        }

        ThreadEntity threadEntity = new ThreadEntity();

        if ( createThreadEntityModel.postId() != null ) {
            threadEntity.setPostId( createThreadEntityModel.postId() );
        }
        if ( createThreadEntityModel.authorId() != null ) {
            threadEntity.setAuthorId( createThreadEntityModel.authorId() );
        }
        if ( createThreadEntityModel.content() != null ) {
            threadEntity.setContent( createThreadEntityModel.content() );
        }

        return threadEntity;
    }

    @Override
    public void updateEntityFromUpdateModel(ThreadEntity threadEntity, UpdateThreadEntityModel updateThreadEntityModel) {
        if ( updateThreadEntityModel == null ) {
            return;
        }

        if ( updateThreadEntityModel.id() != null ) {
            threadEntity.setId( updateThreadEntityModel.id() );
        }
        if ( updateThreadEntityModel.content() != null ) {
            threadEntity.setContent( updateThreadEntityModel.content() );
        }
    }
}
