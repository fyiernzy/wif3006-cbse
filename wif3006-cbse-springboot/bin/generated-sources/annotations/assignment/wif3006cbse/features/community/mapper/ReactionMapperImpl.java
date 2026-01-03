package assignment.wif3006cbse.features.community.mapper;

import assignment.wif3006cbse.features.community.domain.entity.Reaction;
import assignment.wif3006cbse.features.community.domain.vo.ReactionSourceTypeEnum;
import assignment.wif3006cbse.features.community.dto.reaction.CreateReactionModel;
import assignment.wif3006cbse.features.community.dto.reaction.ReactionModel;
import assignment.wif3006cbse.features.community.dto.reaction.UpdateReactionModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-03T14:49:16+0800",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class ReactionMapperImpl implements ReactionMapper {

    @Override
    public ReactionModel toModel(Reaction reaction) {
        if ( reaction == null ) {
            return null;
        }

        String id = null;
        ReactionSourceTypeEnum sourceType = null;
        String sourceId = null;
        String authorId = null;
        String reaction1 = null;

        if ( reaction.getId() != null ) {
            id = reaction.getId();
        }
        if ( reaction.getSourceType() != null ) {
            sourceType = reaction.getSourceType();
        }
        if ( reaction.getSourceId() != null ) {
            sourceId = reaction.getSourceId();
        }
        if ( reaction.getAuthorId() != null ) {
            authorId = reaction.getAuthorId();
        }
        if ( reaction.getReaction() != null ) {
            reaction1 = reaction.getReaction();
        }

        ReactionModel reactionModel = new ReactionModel( id, sourceType, sourceId, authorId, reaction1 );

        return reactionModel;
    }

    @Override
    public Reaction toEntity(CreateReactionModel createReactionModel) {
        if ( createReactionModel == null ) {
            return null;
        }

        Reaction reaction = new Reaction();

        if ( createReactionModel.authorId() != null ) {
            reaction.setAuthorId( createReactionModel.authorId() );
        }
        if ( createReactionModel.reaction() != null ) {
            reaction.setReaction( createReactionModel.reaction() );
        }
        if ( createReactionModel.sourceId() != null ) {
            reaction.setSourceId( createReactionModel.sourceId() );
        }
        if ( createReactionModel.sourceType() != null ) {
            reaction.setSourceType( createReactionModel.sourceType() );
        }

        return reaction;
    }

    @Override
    public void updateEntityFromUpdateModel(Reaction reaction, UpdateReactionModel updateReactionModel) {
        if ( updateReactionModel == null ) {
            return;
        }

        if ( updateReactionModel.reaction() != null ) {
            reaction.setReaction( updateReactionModel.reaction() );
        }
    }
}
