package assignment.wif3006cbse.features.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UpdatePaymentTransactionModel(
        @NotNull java.util.UUID id,
        @NotNull String userId,
        @NotNull String projectId,
        @NotNull java.util.UUID paymentMethodId,
        @NotNull BigDecimal amount,
        @NotNull LocalDateTime transactionDate,
        @NotBlank String status,
        String invoiceId) {
}
