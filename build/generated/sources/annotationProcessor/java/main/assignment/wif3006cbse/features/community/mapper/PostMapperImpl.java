package assignment.wif3006cbse.features.community.mapper;

import assignment.wif3006cbse.features.community.domain.entity.Post;
import assignment.wif3006cbse.features.community.dto.post.CreatePostModel;
import assignment.wif3006cbse.features.community.dto.post.PostModel;
import assignment.wif3006cbse.features.community.dto.post.UpdatePostModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-16T12:30:24+0800",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostModel toModel(Post post) {
        if ( post == null ) {
            return null;
        }

        String id = null;
        String authorId = null;
        String title = null;
        String content = null;

        if ( post.getId() != null ) {
            id = post.getId();
        }
        if ( post.getAuthorId() != null ) {
            authorId = post.getAuthorId();
        }
        if ( post.getTitle() != null ) {
            title = post.getTitle();
        }
        if ( post.getContent() != null ) {
            content = post.getContent();
        }

        PostModel postModel = new PostModel( id, authorId, title, content );

        return postModel;
    }

    @Override
    public Post toEntity(CreatePostModel createPostModel) {
        if ( createPostModel == null ) {
            return null;
        }

        Post post = new Post();

        if ( createPostModel.authorId() != null ) {
            post.setAuthorId( createPostModel.authorId() );
        }
        if ( createPostModel.title() != null ) {
            post.setTitle( createPostModel.title() );
        }
        if ( createPostModel.content() != null ) {
            post.setContent( createPostModel.content() );
        }

        return post;
    }

    @Override
    public void updateEntityFromUpdateModel(Post post, UpdatePostModel updatePostModel) {
        if ( updatePostModel == null ) {
            return;
        }

        if ( updatePostModel.title() != null ) {
            post.setTitle( updatePostModel.title() );
        }
        if ( updatePostModel.content() != null ) {
            post.setContent( updatePostModel.content() );
        }
    }
}
