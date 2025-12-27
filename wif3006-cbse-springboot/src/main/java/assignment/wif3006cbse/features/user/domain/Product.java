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
@Table(name = "product")
@EqualsAndHashCode(callSuper = true)
public class Product extends Auditable {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "product_date")
    private Date date;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "user_id")
    private String userId;
}
