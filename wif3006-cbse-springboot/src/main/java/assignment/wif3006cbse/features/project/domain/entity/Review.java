package assignment.wif3006cbse.features.project.domain.entity;

import assignment.wif3006cbse.config.audit.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@Table(name = "review")
@EqualsAndHashCode(callSuper = true)
public class Review extends Auditable {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "satisfaction_rating")
    private Double satisfactionRating;

    @Column(name = "project_rating")
    private Double projectRating;

    @Column(name = "project_feedback", columnDefinition = "TEXT")
    private String projectFeedback;

    @Column(name = "collaborator_rating")
    private Double collaboratorRating;

    @Column(name = "collaborator_feedback", columnDefinition = "TEXT")
    private String collaboratorFeedback;
}
