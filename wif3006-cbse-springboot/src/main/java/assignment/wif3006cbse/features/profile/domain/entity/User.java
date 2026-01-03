package assignment.wif3006cbse.features.profile.domain.entity;

import assignment.wif3006cbse.config.audit.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "ProfileUser")
@Table(name = "profile_user")
@EqualsAndHashCode(callSuper = true)
public class User extends Auditable {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "about")
    private String about;

    @Column(name = "location")
    private String location;

    @ElementCollection
    @CollectionTable(name = "profile_user_categories", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "category")
    private List<String> categories = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "profile_user_skills", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "skill")
    private List<String> skills = new ArrayList<>();

    @Column(name = "is_profile_public", nullable = false)
    private boolean isProfilePublic = true;
}
