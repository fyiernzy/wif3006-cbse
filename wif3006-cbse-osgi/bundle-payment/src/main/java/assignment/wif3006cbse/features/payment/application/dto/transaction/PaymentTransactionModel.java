package assignment.wif3006cbse.features.payment.application.dto.transaction;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record PaymentTransactionModel(
        String id,
        String userId,
        String projectId,
        String paymentMethodId,
        BigDecimal amount,
        LocalDateTime transactionDate,
        String status,
        String invoiceId) {
}
