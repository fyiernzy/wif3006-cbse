package assignment.wif3006cbse.features.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentTransactionModel(
        java.util.UUID id,
        String userId,
        String projectId,
        java.util.UUID paymentMethodId,
        BigDecimal amount,
        LocalDateTime transactionDate,
        String status,
        String invoiceId) {
}
