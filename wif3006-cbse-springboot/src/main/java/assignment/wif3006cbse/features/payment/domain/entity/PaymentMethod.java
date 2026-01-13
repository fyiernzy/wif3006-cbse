package assignment.wif3006cbse.features.payment.domain.entity;

import assignment.wif3006cbse.config.audit.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "payment_method")
@EqualsAndHashCode(callSuper = true)
public class PaymentMethod extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private java.util.UUID id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "type", nullable = false)
    private String type; // BANK, WALLET, CARD

    @Column(name = "provider", nullable = false)
    private String provider; // e.g., Maybank, GrabPay, Visa

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "expiry_date")
    private String expiryDate; // MM/YY for cards

    @Column(name = "is_default")
    private Boolean isDefault;
}
