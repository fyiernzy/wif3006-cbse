//package assignment.wif3006cbse.features.community.domain.entity;
//
//import assignment.wif3006cbse.config.audit.Auditable;
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import org.hibernate.annotations.UuidGenerator;
//import org.springframework.util.MimeType;
//
//@Data
//@Entity
//@Table(name = "image")
//@EqualsAndHashCode(callSuper = true)
//public class Image extends Auditable {
//
//    @Id
//    @UuidGenerator
//    private String id;
//
//    @Column(name = "path", updatable = false, nullable = false)
//    private String path;
//
//    @Column(name = "name", updatable = false, nullable = false)
//    private String name;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "mime_type", updatable = false, nullable = false)
//    private MimeType mimeType;
//}
