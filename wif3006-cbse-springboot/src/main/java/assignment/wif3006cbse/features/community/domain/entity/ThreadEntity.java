package assignment.wif3006cbse.features.community.domain.entity;

import assignment.wif3006cbse.config.audit.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@Table(name = "thread")
@EqualsAndHashCode(callSuper = true)
public class ThreadEntity extends Auditable {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "post_id", updatable = false, nullable = false)
    private String postId;

    @Column(name = "author_id", updatable = false, nullable = false)
    private String authorId;

    @Column(name = "content", nullable = false)
    private String content;
}
