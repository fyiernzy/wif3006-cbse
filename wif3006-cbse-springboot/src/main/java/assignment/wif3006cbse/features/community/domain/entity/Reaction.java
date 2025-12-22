package assignment.wif3006cbse.features.community.domain.entity;

import assignment.wif3006cbse.config.audit.Auditable;
import assignment.wif3006cbse.features.community.domain.vo.ReactionSourceTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@Table(name = "reaction")
@EqualsAndHashCode(callSuper = true)
public class Reaction extends Auditable {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "source_type")
    @Enumerated(EnumType.STRING)
    private ReactionSourceTypeEnum sourceType;

    @Column(name = "source_id", nullable = false, updatable = false)
    private String sourceId;

    @Column(name = "author_id", nullable = false, updatable = false)
    private String authorId;

    @Column(name = "reaction", updatable = false, nullable = false)
    private String reaction;
}
