package assignment.wif3006cbse.features.sample.domain;

import assignment.wif3006cbse.config.audit.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "sample")
@EqualsAndHashCode(callSuper = true)
public class Sample extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(name = "value", updatable = false, nullable = false)
    private String value;

    @Enumerated(EnumType.STRING)
    @Column(name = "value", nullable = false)
    private SampleStatusEnum sampleStatusEnum;
}
