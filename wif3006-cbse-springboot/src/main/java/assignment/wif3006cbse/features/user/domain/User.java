package assignment.wif3006cbse.features.user.domain;

import assignment.wif3006cbse.config.audit.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UuidGenerator;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends Auditable {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role = UserRole.RECRUITER;

    @Column(name = "profile_pic")
    private byte[] profilePic;

    @Column(name = "headline")
    private String headline;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @ElementCollection
    @Column(name = "categories")
    private List<String> categories;

    @Column(name = "university")
    private String university;

    @ElementCollection
    @Column(name = "skills")
    private List<String> skills;

    @Column(name = "about", columnDefinition = "TEXT")
    private String about;

    @Column(name = "rating")
    private String rating = "5";

    @ElementCollection
    @Column(name = "favorite_projects")
    private List<String> favoriteProjects;

    @ElementCollection
    @Column(name = "taken_projects")
    private List<String> takenProjects;

    @ElementCollection
    @Column(name = "applying_projects")
    private List<String> applyingProjects;

    @ElementCollection
    @Column(name = "completed_projects")
    private List<String> completedProjects;

    @ElementCollection
    @Column(name = "posted_projects")
    private List<String> postedProjects;

    @ElementCollection
    @Column(name = "experience_ids")
    private List<String> experienceIds;

    @ElementCollection
    @Column(name = "product_ids")
    private List<String> productIds;
}
