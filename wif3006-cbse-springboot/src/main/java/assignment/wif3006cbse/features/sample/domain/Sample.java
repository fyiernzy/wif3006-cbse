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
    @Column(name = "sample_value", updatable = false, nullable = false)
    private String sampleValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "sample_status_enum", nullable = false)
    private SampleStatusEnum sampleStatusEnum;
}
