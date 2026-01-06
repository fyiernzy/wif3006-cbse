package assignment.wif3006cbse.features.profile.domain.entity;

import assignment.wif3006cbse.config.audit.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "ProfileJobHistory")
@Table(name = "profile_job_history")
@EqualsAndHashCode(callSuper = true)
public class JobHistory extends Auditable {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "project_id", nullable = false)
    private String projectId;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "status", nullable = false)
    private String status = "Ongoing";

    @Column(name = "rating")
    private Integer rating;

    @Lob
    @Column(name = "review")
    private String review;

    @ElementCollection
    @CollectionTable(name = "profile_job_history_deliverables", joinColumns = @JoinColumn(name = "job_history_id"))
    @Column(name = "deliverable")
    private List<String> deliverables = new ArrayList<>();
}
