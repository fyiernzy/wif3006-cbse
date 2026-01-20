package assignment.wif3006cbse.features.payment.application.dto.transaction;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.math.BigDecimal;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record CreatePaymentTransactionModel(
        String userId,
        String projectId,
        String paymentMethodId,
        BigDecimal amount) {
}
