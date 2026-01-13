package assignment.wif3006cbse.features.payment.domain.entity;

import assignment.wif3006cbse.config.audit.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payment_transaction")
@EqualsAndHashCode(callSuper = true)
public class PaymentTransaction extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private java.util.UUID id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "project_id", nullable = false)
    private String projectId;

    @Column(name = "payment_method_id", nullable = false)
    private java.util.UUID paymentMethodId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name = "status", nullable = false)
    private String status; // PENDING, COMPLETED, FAILED

    @Column(name = "invoice_id")
    private String invoiceId;
}
