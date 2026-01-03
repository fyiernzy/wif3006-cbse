package assignment.wif3006cbse.features.community.mapper;

import assignment.wif3006cbse.features.community.domain.entity.Post;
import assignment.wif3006cbse.features.community.dto.post.CreatePostModel;
import assignment.wif3006cbse.features.community.dto.post.PostModel;
import assignment.wif3006cbse.features.community.dto.post.UpdatePostModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-03T14:49:16+0800",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
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
        if ( createPostModel.content() != null ) {
            post.setContent( createPostModel.content() );
        }
        if ( createPostModel.title() != null ) {
            post.setTitle( createPostModel.title() );
        }

        return post;
    }

    @Override
    public void updateEntityFromUpdateModel(Post post, UpdatePostModel updatePostModel) {
        if ( updatePostModel == null ) {
            return;
        }

        if ( updatePostModel.content() != null ) {
            post.setContent( updatePostModel.content() );
        }
        if ( updatePostModel.title() != null ) {
            post.setTitle( updatePostModel.title() );
        }
    }
}
