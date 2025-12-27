package assignment.wif3006cbse.features.user.domain;

import assignment.wif3006cbse.config.audit.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UuidGenerator;
import java.util.Date;

@Data
@Entity
@Table(name = "experience")
@EqualsAndHashCode(callSuper = true)
public class Experience extends Auditable {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "employment_type")
    private String employmentType;

    @Column(name = "company")
    private String company;

    @Column(name = "location")
    private String location;

    @Column(name = "location_type")
    private String locationType;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_current")
    private Boolean current;

    @Column(name = "start_date")
    private Date from;

    @Column(name = "end_date")
    private Date until;

    @Column(name = "user_id")
    private String userId;
}
