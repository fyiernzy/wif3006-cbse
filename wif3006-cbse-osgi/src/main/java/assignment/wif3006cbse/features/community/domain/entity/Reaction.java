package assignment.wif3006cbse.features.community.domain.entity;

import assignment.wif3006cbse.features.community.domain.vo.ReactionSourceTypeEnum;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class Reaction implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private ReactionSourceTypeEnum sourceType;
    private String sourceId;
    private String authorId;
    private String reaction;

    public Reaction() {
        this.id = UUID.randomUUID().toString();
    }

    public Reaction(
        ReactionSourceTypeEnum sourceType,
        String sourceId,
        String authorId,
        String reaction
    ) {
        this.id = UUID.randomUUID().toString();
        this.sourceType = sourceType;
        this.sourceId = sourceId;
        this.authorId = authorId;
        this.reaction = reaction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReactionSourceTypeEnum getSourceType() {
        return sourceType;
    }

    public void setSourceType(ReactionSourceTypeEnum sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }
}
