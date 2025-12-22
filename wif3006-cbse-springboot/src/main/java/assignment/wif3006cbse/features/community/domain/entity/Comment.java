package assignment.wif3006cbse.features.community.domain.entity;

import assignment.wif3006cbse.config.audit.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
@Table(name = "comment")
@EqualsAndHashCode(callSuper = true)
public class Comment extends Auditable {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "thread_id", updatable = false, nullable = false)
    private String threadId;

    @Column(name = "author_id", updatable = false, nullable = false)
    private String authorId;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;
}
