package assignment.wif3006cbse.features.project.domain.entity;

import assignment.wif3006cbse.config.audit.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Date;

@Data
@Entity
@Table(name = "project")
@EqualsAndHashCode(callSuper = true)
public class Project extends Auditable {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "posted_by", nullable = false)
    private String postedBy;

    @Column(name = "company_logo")
    private byte[] companyLogo;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "project_title", nullable = false)
    private String projectTitle;

    @Column(name = "project_description", nullable = false)
    private String projectDescription;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "project_category", nullable = false)
    private String projectCategory;

    @Column(name = "project_duration", nullable = false)
    private String projectDuration;

    @ElementCollection
    @Column(name = "filter", nullable = false)
    private List<String> filter;

    @Column(name = "contact_information", nullable = false)
    private String contactInformation;

    @Column(name = "additional_notes")
    private String additionalNotes;

    @Column(name = "deadline", nullable = false)
    private Date deadline;

    @Column(name = "project_budget", nullable = false)
    private Double projectBudget;

    @ElementCollection
    @Column(name = "required_skills", nullable = false)
    private List<String> requiredSkills;

    @Column(name = "agreed_to_terms", nullable = false)
    private Boolean agreedToTerms;

    @Column(name = "posted")
    private Boolean posted;

    @Column(name = "taken")
    private Boolean taken;

    @Column(name = "completed")
    private Boolean completed;

    @ElementCollection
    @Column(name = "applicants")
    private List<String> applicants;

    @Column(name = "service_provider")
    private String serviceProvider;

    @Column(name = "file_accepted")
    private Boolean fileAccepted;

    @ElementCollection
    @Column(name = "uploaded_file_ids")
    private List<String> uploadedFileIds;
}
